package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal {
	public BlobGoal(Color c) {
		super(c);
	}

	public int score(Block board) {
		Color[][] c = board.flatten();
		boolean[][] b = new boolean[c.length][c.length];
		int maxBlobSize = 0;

		for(int i = 0; i < c.length; ++i) {
			for(int j = 0; j < c[0].length; ++j) {
				int temp = this.undiscoveredBlobSize(j, i, c, b);
				if (temp > maxBlobSize) {
					maxBlobSize = temp;
				}
			}
		}
		return maxBlobSize;
	}

	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(this.targetGoal) + " blocks, anywhere within the block";
	}

	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		if (i >= 0 && i < unitCells.length && j >= 0 && j < unitCells.length) {
			if (unitCells[i][j] == this.targetGoal && !visited[i][j]) {
				visited[i][j] = true;
				return 1 + this.undiscoveredBlobSize(i - 1, j, unitCells, visited) + this.undiscoveredBlobSize(i + 1, j, unitCells, visited) + this.undiscoveredBlobSize(i, j - 1, unitCells, visited) + this.undiscoveredBlobSize(i, j + 1, unitCells, visited);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
}
