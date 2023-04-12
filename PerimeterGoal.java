package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal {
	public PerimeterGoal(Color c) {
		super(c);
	}

	public int score(Block board) {
		Color[][] flattened = board.flatten();
		int maxBlobSize = 0;

		for(int i = 0; i < flattened.length; ++i) {
			for(int j = 0; j < flattened[i].length; ++j) {
				if (i == 0 && flattened[i][j] == this.targetGoal) {
					++maxBlobSize;
				}

				if (i == flattened.length - 1 && flattened[i][j] == this.targetGoal) {
					++maxBlobSize;
				}

				if (j == 0 && flattened[i][j] == this.targetGoal) {
					++maxBlobSize;
				}

				if (j == flattened[0].length - 1 && flattened[i][j] == this.targetGoal) {
					++maxBlobSize;
				}
			}
		}

		return maxBlobSize;
	}

	public String description() {
		return "Place the highest number of " + GameColors.colorToString(this.targetGoal) + " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}
}
