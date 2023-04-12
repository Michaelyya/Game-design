package assignment3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Block {
 private int xCoord;
 private int yCoord;
 private int size;
 private int level;
 private int maxDepth;
 private Color color;
 private Block[] children;
 public static Random gen = new Random();

 public Block() {
 }

 public Block(int x, int y, int size, int lvl, int maxD, Color c, Block[] subBlocks) {
  this.xCoord = x;
  this.yCoord = y;
  this.size = size;
  this.level = lvl;
  this.maxDepth = maxD;
  this.color = c;
  this.children = subBlocks;
 }

 public Block(int lvl, int maxDepth) {
  if (lvl <= maxDepth) {
   this.level = lvl;
   this.maxDepth = maxDepth;
   if (lvl == maxDepth) {
    this.children = new Block[0];
    this.color = GameColors.BLOCK_COLORS[gen.nextInt(GameColors.BLOCK_COLORS.length)];
   } else {
    double num = gen.nextDouble();
    if (num < Math.exp(-0.25 * (double)this.level)) {
     this.children = new Block[4];
     this.children[0] = new Block(lvl + 1, maxDepth);
     this.children[1] = new Block(lvl + 1, maxDepth);
     this.children[2] = new Block(lvl + 1, maxDepth);
     this.children[3] = new Block(lvl + 1, maxDepth);
    } else {
     this.children = new Block[0];
     this.color = GameColors.BLOCK_COLORS[gen.nextInt(GameColors.BLOCK_COLORS.length)];
    }
   }
  }
  else{ throw new IllegalArgumentException();
  }
 }

 public void updateSizeAndPosition(int size, int xCoord, int yCoord) {
  if ((size % 2 != 0) && this.level < this.maxDepth) {
   throw new IllegalArgumentException();
  } else if(size <= 0){
   throw new IllegalArgumentException();
  }else {
   this.size = size;
   this.xCoord = xCoord;
   this.yCoord = yCoord;
   if (this.children.length != 0) {
    int halfSize = size / 2;
    this.children[0].updateSizeAndPosition(halfSize, xCoord + halfSize, yCoord);
    this.children[1].updateSizeAndPosition(halfSize, xCoord, yCoord);
    this.children[2].updateSizeAndPosition(halfSize, xCoord, yCoord + halfSize);
    this.children[3].updateSizeAndPosition(halfSize, xCoord + halfSize, yCoord + halfSize);
   }

  }
 }

 public ArrayList<BlockToDraw> getBlocksToDraw() {
  ArrayList<BlockToDraw> blockToDraws = new ArrayList();
  if (this.children.length != 0) {
   Block[] var2 = this.children;
   int var3 = var2.length;

   for(int var4 = 0; var4 < var3; ++var4) {
    Block child = var2[var4];
    blockToDraws.addAll(child.getBlocksToDraw());
   }
  } else {
   BlockToDraw block = new BlockToDraw(this.color, this.xCoord, this.yCoord, this.size, 0);
   BlockToDraw frame = new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord, this.yCoord, this.size, 3);
   blockToDraws.add(block);
   blockToDraws.add(frame);
  }

  return blockToDraws;
 }

 public BlockToDraw getHighlightedFrame() {
  return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
 }

 public Block getSelectedBlock(int x, int y, int lvl) {
  if (lvl >= this.level && lvl <= this.maxDepth) {
   if (!this.isContain(x, y)) {
    return null;
   } else {
    if (this.level < lvl) {
     Block[] var4 = this.children;
     int var5 = var4.length;

     for(int var6 = 0; var6 < var5; ++var6) {
      Block b = var4[var6];
      if (b.xCoord <= x && x <= b.xCoord + b.size && b.yCoord <= y && y <= b.yCoord + b.size) {
       return b.getSelectedBlock(x, y, lvl);
      }
     }
    }

    return this;
   }
  } else {
   throw new IllegalArgumentException();
  }
 }

 public void reflect(int direction) {
  if (direction != 0 && direction != 1) {
   throw new IllegalArgumentException();
  } else if (this.children.length != 0) {
   int midY;
   Block[] temp;
   if (direction == 0) {
    midY = this.yCoord + this.size / 2;
    this.reflectX_axis(midY);
    temp = new Block[]{this.children[3], this.children[2], this.children[1], this.children[0]};
    this.children = temp;
   } else {
    midY = this.xCoord + this.size / 2;
    this.reflectY_axis(midY);
    temp = new Block[]{this.children[1], this.children[0], this.children[3], this.children[2]};
    this.children = temp;
   }

  }
 }

 private void reflectX_axis(int mid) {
  this.yCoord = mid + (mid - this.yCoord) - this.size;
  if (this.children.length != 0) {
   for(int i = 0; i < this.children.length; ++i) {
    this.children[i].reflectX_axis(mid);
   }
  }

 }

 private void reflectY_axis(int mid) {
  this.xCoord = mid + (mid - this.xCoord) - this.size;
  if (this.children.length != 0) {
   for(int i = 0; i < this.children.length; ++i) {
    this.children[i].reflectY_axis(mid);
   }
  }

 }

 public void rotate(int direction) {
  if (direction != 0 && direction != 1) {
   throw new IllegalArgumentException();
  } else if (this.children.length != 0) {
   Block[] temp;
   int i;
   if (direction == 1) {
    temp = new Block[]{this.children[1], this.children[2], this.children[3], this.children[0]};
    this.children = temp;
    this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);

    for(i = 0; i < this.children.length; ++i) {
     this.children[i].rotate(1);
    }
   } else {
    temp = new Block[]{this.children[3], this.children[0], this.children[1], this.children[2]};
    this.children = temp;
    this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);

    for(i = 0; i < this.children.length; ++i) {
     this.children[i].rotate(0);
    }
   }

  }
 }

 public boolean smash() {
  if (this.level != 0 && this.level < this.maxDepth) {
   this.children = new Block[4];

   for(int i = 0; i < 4; ++i) {
    this.children[i] = new Block(this.level + 1, this.maxDepth);
   }

   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
   return true;
  } else {
   return false;
  }
 }

 public Color[][] flatten() {
  int length = (int)Math.pow(2.0, (double)this.maxDepth);
  this.updateSizeAndPosition(length, 0, 0);
  Color[][] colors = new Color[length][length];
  this.fill(colors);
  return colors;
 }

 private void fill(Color[][] colors) {
  int childLength;
  if (this.children.length != 0) {
   for(childLength = 0; childLength < this.children.length; ++childLength) {
    this.children[childLength].fill(colors);
   }
  } else {
   childLength = (int)Math.pow(2.0, (double)(this.maxDepth - this.level));

   for(int i = this.xCoord; i < this.xCoord + childLength; ++i) {
    for(int j = this.yCoord; j < this.yCoord + childLength; ++j) {
     colors[j][i] = this.color;
    }
   }
  }

 }

 public int getMaxDepth() {
  return this.maxDepth;
 }

 public int getLevel() {
  return this.level;
 }

 public String toString() {
  return String.format("pos=(%d,%d), size=%d, level=%d", this.xCoord, this.yCoord, this.size, this.level);
 }

 public void printBlock() {
  this.printBlockIndented(0);
 }

 private void printBlockIndented(int indentation) {
  String indent = "";

  for(int i = 0; i < indentation; ++i) {
   indent = indent + "\t";
  }

  if (this.children.length == 0) {
   String colorInfo = GameColors.colorToString(this.color) + ", ";
   System.out.println(indent + colorInfo + String.valueOf(this));
  } else {
   System.out.println(indent + String.valueOf(this));
   Block[] var8 = this.children;
   int var4 = var8.length;

   for(int var5 = 0; var5 < var4; ++var5) {
    Block b = var8[var5];
    b.printBlockIndented(indentation + 1);
   }
  }

 }

 private static void coloredPrint(String message, Color color) {
  System.out.print(GameColors.colorToANSIColor(color));
  System.out.print(message);
  System.out.print(GameColors.colorToANSIColor(Color.WHITE));
 }

 public void printColoredBlock() {
  Color[][] colorArray = this.flatten();
  Color[][] var2 = colorArray;
  int var3 = colorArray.length;

  for(int var4 = 0; var4 < var3; ++var4) {
   Color[] colors = var2[var4];
   Color[] var6 = colors;
   int var7 = colors.length;

   for(int var8 = 0; var8 < var7; ++var8) {
    Color value = var6[var8];
    String colorName = GameColors.colorToString(value).toUpperCase();
    if (colorName.length() == 0) {
     colorName = "█";
    } else {
     colorName = colorName.substring(0, 1);
    }

    coloredPrint(colorName, value);
   }

   System.out.println();
  }

 }

 private boolean isContain(int x, int y) {
  return x >= this.xCoord && x < this.xCoord + this.size && y >= this.yCoord && y < this.yCoord + this.size;
 }

 public static void main(String[] args) {
  Block blockDepth2 = new Block(0, 2);
  blockDepth2.printColoredBlock();
 }
}
