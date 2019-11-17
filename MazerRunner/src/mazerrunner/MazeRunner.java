package mazerrunner;
import java.util.*;

public class MazeRunner {
    private int dimensionX, dimensionY; // chieu rong va dai cua maze
    private Cell[][] cells; // mang 2 chieu chua cac phan tu
    private Random random = new Random(); // tao random

    private void init() {
        // tao cells
        cells = new Cell[dimensionX][dimensionY];
        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                cells[x][y] = new Cell(x, y, random.nextInt(9) + 1);
            }
        }
        cells[0][0].point = 0;
        cells[dimensionX-1][dimensionY-1].point = 0;

    }

    public class Cell {
        int x, y; // toa do
        // cac cells ben canh ma co the di toi
        ArrayList<Cell> neighbors = new ArrayList<>();
        // bien check xem cell nay da tung dung chua de tao maze
        boolean checked = false;
        //diem khi di qua o
        int point;
        // dung khi solve xem da di qua chua
        boolean visited = false;
        // tong diem khi di toi cell hien tai
        double totalsPoint;
        // bien xac dinh co phai la duong solve khong
        boolean inPath = false;
        // constructor cell tai x,y
        Cell(int x, int y, int point) {
            this.x = x;
            this.y = y;
            this.point = point;
        }

        // them hang xom co the di toi vao cell nay va cell hang xom
        void addNeighbor(Cell other) {
            if (!this.neighbors.contains(other)) {
                this.neighbors.add(other);
            }
            if (!other.neighbors.contains(this)) {
                other.neighbors.add(this);
            }
        }

        // override equals
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Cell)) return false;
            Cell otherCell = (Cell) other;
            return (this.x == otherCell.x && this.y == otherCell.y);
        }

        @Override
        public int hashCode() {
            // random hash code method designed to be usually unique
            return this.x + this.y * 256;
        }


    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        } catch (ArrayIndexOutOfBoundsException e) { // catch exception qua phan tu cua mang
            return null;
        }
    }

    // contructor tao maze
    private void generateMaze() {
        generateMaze(0, 0);
    }

    // tao maze tai diem co toa do x,y
    private void generateMaze(int x, int y) {
        generateMaze(getCell(x, y));
    }

    private void generateMaze(Cell startAt) {
        if (startAt == null) return;
        startAt.checked = true;
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(startAt);
        while (!cells.isEmpty()) {
            Cell cell = null;
            if (random.nextInt(10) == 0)
                cell = cells.remove(random.nextInt(cells.size()));
            else cell = cells.remove(cells.size() - 1);
            // mang lu tru tat ca cac hang xom cua cell di toi
            ArrayList<Cell> neighbors = new ArrayList<>();
            // cells co di toi
            Cell[] potentialNeighbors = new Cell[]{
                    getCell(cell.x + 1, cell.y),
                    getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y),
                    getCell(cell.x, cell.y - 1)
            };
            for (Cell other : potentialNeighbors) {
                // them vao mang neighbors neu khac rong va chua su dung
                if (other == null || other.checked) continue;
                neighbors.add(other);
            }
            if (neighbors.isEmpty()) continue;
            // chon cell bat ki trong cac hang xom tiem nang
            Cell selected = neighbors.get(random.nextInt(neighbors.size()));
            selected.checked = true;
            // add them vao hang xom co the di toi
            cell.addNeighbor(selected);
            cells.add(cell);
            cells.add(selected);
        }
        // random mo them 4 duong di
        for (int i = 0; i < 4; i++) {
            Cell cell = getCell(random.nextInt(dimensionX), random.nextInt(dimensionY));
            ArrayList<Cell> neighbors = new ArrayList<>();
            // cells co di toi
            Cell[] potentialNeighbors = new Cell[]{
                    getCell(cell.x + 1, cell.y),
                    getCell(cell.x, cell.y + 1),
                    getCell(cell.x - 1, cell.y),
                    getCell(cell.x, cell.y - 1)
            };
            for (Cell other : potentialNeighbors) {
                // them vao mang neighbors neu khac rong va chua su dung
                if (other == null) continue;
                neighbors.add(other);
            }
            for (Cell other : neighbors) {
                if (other != null && !cell.neighbors.contains(other)) {
                    cell.addNeighbor(other);
                    break;
                }
            }

        }
    }

    public void solve() {
        // giai maze tu diem (0,0)
        this.solve(0, 0, dimensionX - 1, dimensionY - 1);
    }

    public void solve(int startX, int startY, int endX, int endY) {
        // re initialize cells for path finding
        for (Cell[] cellrow : this.cells) {
            for (Cell cell : cellrow) {
                cell.visited = false;
            }
        }
        // cells still being considered
        ArrayList<Cell> openCells = new ArrayList<>();
        // cell being considered
        Cell endCell = getCell(endX, endY);
        Cell currentCell = getCell(startX, startY);
        if (endCell == null || currentCell == null) return;
        currentCell.visited = true;
        currentCell.totalsPoint = 0;
        while (currentCell != endCell) {
            // sort theo diem nhan dc khi qua cell tu cao xuong thap
            Collections.sort(currentCell.neighbors, new Comparator<Cell>() {
                @Override
                public int compare(Cell cell1, Cell cell2) {
                    double diff = cell2.point - cell1.point;
                    if (diff > 0) return 1;
                    else if (diff < 0) return -1;
                    else return 0;
                }
            });
            int checksolve = 0;
            for (Cell neighbor : currentCell.neighbors) {
                if (!neighbor.visited) {
                    openCells.add(currentCell);
                    this.cells[currentCell.x][currentCell.y].inPath = true;
                    neighbor.visited = true;
                    neighbor.totalsPoint = neighbor.point + currentCell.point;
                    openCells.add(currentCell);
                    currentCell = neighbor;
                    checksolve = 1;
                    break;
                }
            }
            if (checksolve == 0) { // neu k di duoc den vi tri nao nua
                this.cells[currentCell.x][currentCell.y].inPath = false;
                currentCell = openCells.remove(openCells.size() - 1);// back ve vi tri truoc do de tim duong
            }
        }
        this.cells[dimensionX-1][dimensionY-1].inPath = true;
        System.out.println("solved");

    }


    public void draw() {
        char backChar = ' ', wallChar = 'X', cellChar = ' ', pathChar = '*';
        for (int i = 0; i < dimensionY * 4 + 1; i++) {
            System.out.print(wallChar);
        }
        System.out.println();
        for (int i = 0; i < dimensionX * 2; i++) {
            for (int j = 0; j < dimensionY; j++) {
                if (i % 2 == 0) {
                    if (j == 0) {
                        System.out.print(wallChar);
                    }
                    if(getCell(i/2,j).inPath){
                        System.out.print(" * ");
                    }else System.out.print("   ");
                    if (getCell(i / 2, j + 1) != null) {
                        if (getCell(i / 2, j).neighbors.contains(getCell(i / 2, j + 1))) {
                            System.out.print(cellChar);
                        } else System.out.print(wallChar);
                    } else System.out.print(wallChar);

                } else {
                    if (j == 0) {
                        System.out.print(wallChar);
                    }
                    if (getCell(i / 2 + 1, j) != null) {
                        if (getCell(i / 2, j).neighbors.contains(getCell(i / 2 + 1, j))) {
                            System.out.print("   " + wallChar);
                        } else System.out.print("XXXX");
                    } else if (i == dimensionX * 2 - 1) {
                        System.out.print("XXXX");

                    } else System.out.print(wallChar);
                }
            }
            System.out.println();
        }
        for(int i = 0;i < dimensionX;i++){
            for (int j = 0;j < dimensionY;j++){
                if(getCell(i,j).inPath){
                    System.out.print(getCell(i,j).point + "*| ");
                }else System.out.print(getCell(i,j).point + " | ");

            }
            System.out.println();
        }


    }


    public MazeRunner(int xDimension, int yDimension) {
        dimensionX = xDimension;
        dimensionY = yDimension;
        init();
        generateMaze();
    }

    public static void main(String[] args) {
        MazeRunner maze = new MazeRunner(15, 15);
        maze.solve();
        maze.draw();
    }

}