/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.map;

import TomHopper.grid.HexGrid;
import TomHopper.grid.HexLocation;

/**
 * Class for generating the structure of a HexGrid world map. Specifically, this
 * generator class makes a tree like structure on a HexGrid with probability based
 * island generation.
 *
 * @author ptehr
 */
public class HexMapGenerator {

    // Total number of Islands
    private int total;
    // Current number of Islands placed on map
    private int currTotal;
    // Minimum number of island type for each island type
    // Puzzle Island, Shop Island, Battle Island, Boss Island, Treasure Island
    private static final int[] minCounts = {2, 6, 10, 2, 4};
    // Maximum number of island type for each island type
    // Puzzle Island, Shop Island, Battle Island, Boss Island, Treasure Island
    private static final int[] maxCounts = {3, 8, 12, 4, 7};
    // datafield for the HexGrid
    private HexGrid<AbstractMapSpot> hgMap;

    /**
     * Creates a HexMapGenerator object.
     *
     * @param hg HexGrid of AbstractMapSpots
     */
    public HexMapGenerator(HexGrid<AbstractMapSpot> hg) {
        setIslandCounts();
        hgMap = hg;
    }

    /**
     * Generates the Islands on the HexGrid Uses various helper methods to make
     * the code friendly The system of generation is through a probability
     * generating function that can be adjusted to the needs of the game.
     */
    public void generateHexGrid() {
        Object[][] grid = hgMap.getArray();
        int rows = hgMap.getNumRows();
        int cols = hgMap.getNumCols();

        currTotal = 1;
        //grid[0][(cols - 1) / 2] = new EmptyIsland();
        while (currTotal != total) {
            //System.out.println(currTotal);
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < hgMap.colsInRow(i); ++j) {
                    if (i == 0 && j == (cols - 1) / 2) {
                        generateThreePath(i, j);
                    } else if (grid[i][j] != null) {
                        double prob = Math.random();
                        if (i < 0.3 * rows) {
                            if (prob < 0.6) {
                                generateOnePath(i, j);
                            } else {
                                generateTwoPath(i, j);
                            }
                        } else if (i < 0.7 * rows) {
                            if (prob < 0.4) {
                                generateTwoPath(i, j);
                            } else if (prob < 0.8) {
                                generateOnePath(i, j);
                            }
                        } else {
                            if (prob < 0.4) {
                                generateOnePath(i, j);
                            }
                        }
                    }
                }
            }
        }
        hexMapIslandPlacement();
    }

    /**
     * Creates a vertical bridge from a given node downward, thus creating a new
     * node on the hexGrid. Bridges are preserved in the nodes(EmptyIslands).
     *
     * @param x Row coordinate
     * @param y Column coordinate
     */
    private void verticalBridge(int x, int y) {
        HexLocation loc = new HexLocation(x, y);
        HexLocation downLoc = loc.getAdjacentHexLocation(270);
        if (downLoc.getRow() >= hgMap.getNumRows()) {
            return;
        }
        if (hgMap.getArray()[downLoc.getRow()][downLoc.getCol()] instanceof EmptyIsland) {
            return;
        }
        int newX = downLoc.getRow();
        int newY = downLoc.getCol();

        Object[][] grid = hgMap.getArray();
        //grid[newX][newY] = new EmptyIsland();
        EmptyIsland newIsle = (EmptyIsland) grid[newX][newY];
        newIsle.addBridge(90);
        EmptyIsland curIsle = (EmptyIsland) grid[x][y];
        curIsle.addBridge(270);
        ++currTotal;
    }

    /**
     * Creates a right down bridge from a given node, thus creating a new node
     * on the hexGrid. Bridges are preserved in the nodes(EmptyIslands).
     *
     * @param x Row coordinate
     * @param y Column coordinate
     */
    private void rightBridge(int x, int y) {
        HexLocation loc = new HexLocation(x, y);
        HexLocation downLoc = loc.getAdjacentHexLocation(330);
        if ((downLoc.getRow() >= hgMap.getNumRows() || downLoc.getCol() >= hgMap.colsInRow(downLoc.getRow()))) {
            return;
        }
        if (hgMap.getArray()[downLoc.getRow()][downLoc.getCol()] instanceof EmptyIsland) {
            return;
        }
        int newX = downLoc.getRow();
        int newY = downLoc.getCol();

        Object[][] grid = hgMap.getArray();
        //grid[newX][newY] = new EmptyIsland();
        EmptyIsland newIsle = (EmptyIsland) grid[newX][newY];
        newIsle.addBridge(150);
        EmptyIsland curIsle = (EmptyIsland) grid[x][y];
        curIsle.addBridge(330);
        ++currTotal;
    }

    /**
     * Creates a left down bridge from a given node downward, thus creating a
     * new node on the hexGrid. Bridges are preserved in the nodes(EmptyIslands).
     *
     * @param x Row coordinate
     * @param y Column coordinate
     */
    private void leftBridge(int x, int y) {
        HexLocation loc = new HexLocation(x, y);
        HexLocation downLoc = loc.getAdjacentHexLocation(210);
        if (downLoc.getRow() >= hgMap.getNumRows() || downLoc.getCol() < 0) {
            return;
        }
        if (hgMap.getArray()[downLoc.getRow()][downLoc.getCol()] instanceof EmptyIsland) {
            return;
        }
        int newX = downLoc.getRow();
        int newY = downLoc.getCol();

        Object[][] grid = hgMap.getArray();
        //grid[newX][newY] = new EmptyIsland();
        EmptyIsland newIsle = (EmptyIsland) grid[newX][newY];
        newIsle.addBridge(30);
        EmptyIsland curIsle = (EmptyIsland) grid[x][y];
        curIsle.addBridge(210);
        ++currTotal;
    }

    /**
     * Generates one random path from a given node.
     *
     * @param i Row coordinate
     * @param j Column coordinate
     */
    private void generateOnePath(int i, int j) {
        double prob = Math.random();
        if (currTotal == total) {
            return;
        }
        if (prob < 0.33) {
            verticalBridge(i, j);
        } else if (prob < 0.66) {
            rightBridge(i, j);
        } else {
            leftBridge(i, j);
        }
    }

    /**
     * Generates two paths from a given node.
     *
     * @param i Row coordinate
     * @param j Column coordinate
     */
    private void generateTwoPath(int i, int j) {
        double prob = Math.random();
        if (currTotal == total) {
            return;
        }
        if (prob < 0.33) {
            verticalBridge(i, j);
            if (currTotal != total) {
                leftBridge(i, j);
            }
        } else if (prob < 0.66) {
            verticalBridge(i, j);
            if (currTotal != total) {
                rightBridge(i, j);
            }
        } else {
            rightBridge(i, j);
            if (currTotal != total) {
                leftBridge(i, j);
            }
        }
    }

    /**
     * Generates three paths from a given node.
     *
     * @param i Row coordinate
     * @param j Column coordinate
     */
    private void generateThreePath(int i, int j) {
        if (currTotal == total) {
            return;
        }
        rightBridge(i, j);
        if (currTotal == total) {
            return;
        }
        leftBridge(i, j);
        if (currTotal == total) {
            return;
        }
        verticalBridge(i, j);
    }

    /**
     * Sets the total number of islands to be on the grid as well as setting the 
     * counts for each individual island type to be shown on grid.
     */
    private void setIslandCounts() {
        total = (int) (Math.random() * 6) + 27;
        int currCount = 0;
        for (int i = 0; i < minCounts.length; ++i) {
            currCount = currCount + minCounts[i];
        }
        while (total > currCount) {
            int hey = (int) (Math.random() * 5);
            if (minCounts[hey] != maxCounts[hey]) {
                ++minCounts[hey];
                ++currCount;
            }
        }
    }

    // Whats happening here with the -11 and also idk whats even going on here
    /**
     * Places islands and bridges onto the newly created Tree on HexGrid.
     */
    private void hexMapIslandPlacement() {
        Object[][] grid = hgMap.getArray();
        boolean[][] check = new boolean[hgMap.getNumRows()][hgMap.getNumCols()];
        for (int i = grid.length - 11; i >= 0; --i) {
            for (int j = 0; j < hgMap.colsInRow(i); ++j) {
                if (grid[i][j] != null && minCounts[3] != 0) {
                    EmptyIsland used = (EmptyIsland) grid[i][j];
                    //BossIsland replace = new BossIsland();
                    //replace.setBridges(used.getBridges());
                    //grid[i][j] = replace;
                    check[i][j] = true;
                    --minCounts[3];
                }
            }
        }
        /*EmptyIsland used1 = (EmptyIsland) grid[0][2];
        BattleIsland replace1 = new BattleIsland();
        replace1.setBridges(used1.getBridges());
        grid[0][2] = replace1;
        check[0][2] = true;
        --minCounts[2];
        used1 = (EmptyIsland) grid[1][2];
        ShopIsland replace2 = new ShopIsland();
        replace2.setBridges(used1.getBridges());
        grid[1][2] = replace2;
        check[1][2] = true;
        --minCounts[1];
        used1 = (EmptyIsland) grid[1][3];
        TreasureIsland replace3 = new TreasureIsland();
        replace3.setBridges(used1.getBridges());
        grid[1][3] = replace3;
        check[1][3] = true;
        --minCounts[4];
        used1 = (EmptyIsland) grid[2][2];
        PuzzleIsland replace4 = new PuzzleIsland();
        replace4.setBridges(used1.getBridges());
        grid[2][2] = replace4;
        check[2][2] = true;
        --minCounts[0];
        // Puzzle Island, Shop Island, Battle Island, Boss Island, Treasure Island
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (check[i][j] != true && grid[i][j] != null) {
                    int index = weightedProb();
                    if (index == 0) {
                        EmptyIsland used = (EmptyIsland) grid[i][j];
                        PuzzleIsland replace = new PuzzleIsland();
                        replace.setBridges(used.getBridges());
                        grid[i][j] = replace;
                        --minCounts[index];
                    } else if (index == 1) {
                        EmptyIsland used = (EmptyIsland) grid[i][j];
                        ShopIsland replace = new ShopIsland();
                        replace.setBridges(used.getBridges());
                        grid[i][j] = replace;
                        --minCounts[index];
                    } else if (index == 2) {
                        EmptyIsland used = (EmptyIsland) grid[i][j];
                        BattleIsland replace = new BattleIsland();
                        replace.setBridges(used.getBridges());
                        grid[i][j] = replace;
                        --minCounts[index];
                    } else if (index == 3) {
                        EmptyIsland used = (EmptyIsland) grid[i][j];
                        BossIsland replace = new BossIsland();
                        replace.setBridges(used.getBridges());
                        grid[i][j] = replace;
                        --minCounts[index];
                    } else {
                        EmptyIsland used = (EmptyIsland) grid[i][j];
                        TreasureIsland replace = new TreasureIsland();
                        replace.setBridges(used.getBridges());
                        grid[i][j] = replace;
                        --minCounts[index];
                    }
                }
            }
        }*/
    }

    // Change explanation
    /**
     * Weighted Probability of island types. Returns an index correlated to an
     * island type.
     * 
     * @return Index of Island for Array
     */
    private int weightedProb() {
        int sum = 0;
        for (int i = 0; i < minCounts.length; ++i) {
            sum = sum + minCounts[i];
        }
        int num = (int) (Math.random() * sum);
        int curr = minCounts[0];
        if (num < curr) {
            return 0;
        }
        curr = curr + minCounts[1];
        if (num < curr) {
            return 1;
        }
        curr = curr + minCounts[2];
        if (num < curr) {
            return 2;
        }
        curr = curr + minCounts[3];
        if (num < curr) {
            return 3;
        }
        return 4;
    }

}
