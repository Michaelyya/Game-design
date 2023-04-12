package Assignment2;

public class ActionQueue extends MyQueue<Direction> {
    private MyStack<Integer> countStack;
    private MyStack<Direction> directionStack;
    private MyStack<Character> parenthesisStack;
    private MyStack<Direction> result;
    private MyStack<Direction> michael;

    Direction S = Direction.SOUTH;
    Direction W = Direction.WEST;
    Direction N = Direction.NORTH;
    Direction E = Direction.EAST;

    public ActionQueue() {
        countStack = new MyStack<Integer>();
        directionStack = new MyStack<Direction>();
        parenthesisStack = new MyStack<>();
        result = new MyStack<Direction>();
        michael = new MyStack<Direction>();
    }

    public void clear() {
        super.clear();
        parenthesisStack.clear();
        result.clear();
        michael.clear();
        countStack.clear();
        directionStack.clear();
    }

    public void loadFromEncodedString(String encodedMessage) throws IllegalArgumentException {
        clear();
        char mine = 0;
        String tempString = "";
        if (checkOnly(encodedMessage)){
            for (int i = 0; i < encodedMessage.length(); i++) {
                if (encodedMessage.charAt(i)==(69)){
                    super.enqueue(Direction.EAST);
                }else if (encodedMessage.charAt(i)==83){
                    super.enqueue(Direction.SOUTH);
                }else if (encodedMessage.charAt(i)==87){
                    super.enqueue(Direction.WEST);
                }else if (encodedMessage.charAt(i)==78){
                    super.enqueue(Direction.NORTH);
                }
            }
        }
        else{
            for (int i = 0; i < encodedMessage.length(); i++) {
                mine = encodedMessage.charAt(i);
                if (Character.isDigit(mine)) {
                    if ((Integer.parseInt(String.valueOf(mine))) < 0) {
                        throw new IllegalArgumentException("num cannot be a negative integer");
                    }
                    else if (Character.isDigit(encodedMessage.charAt(i+1))){
                        tempString += mine;
                        continue;
                    }
                    tempString += mine;
                    countStack.push((Integer.parseInt(tempString)));
                    tempString = "";
                } else if (mine == '[') {
                    if (countStack.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    else if (!directionStack.isEmpty()){
                        int number = countStack.pop();
                        char dir = encodedMessage.charAt(i+1);
                        for (int f = 0; f < number; f++) {
                            if (dir==(69)){
                                directionStack.push(Direction.EAST);
                            }else if (dir==83){
                                directionStack.push(Direction.SOUTH);
                            }else if (dir==87){
                                directionStack.push(Direction.WEST);
                            }else if (dir==78){
                                directionStack.push(Direction.NORTH);
                            }
                        }
                        i += 2;
                    }
                    parenthesisStack.push('[');
                } else if (mine == 'W') {
                    if (countStack.isEmpty()) {
                        if (parenthesisStack.isEmpty()){
                            super.enqueue(Direction.WEST);
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    else{
                        if(parenthesisStack.isEmpty()){
                            throw new IllegalArgumentException();
                        }
                        directionStack.push(W);
                    }
                } else if (mine == 'E') {
                    if (countStack.isEmpty()) {
                        if (parenthesisStack.isEmpty()){
                            super.enqueue(Direction.EAST);
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    else{
                        if(parenthesisStack.isEmpty()){
                            throw new IllegalArgumentException();
                        }
                        directionStack.push(E);
                    }
                } else if (mine == 'S') {
                    if (countStack.isEmpty()) {
                        if (parenthesisStack.isEmpty()){
                            super.enqueue(Direction.SOUTH);
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    else{
                        if(parenthesisStack.isEmpty()){
                            throw new IllegalArgumentException();
                        }
                        directionStack.push(S);
                    }
                } else if (mine == 'N') {
                    if (countStack.isEmpty()) {
                        if (parenthesisStack.isEmpty()){
                            super.enqueue(Direction.NORTH);
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    }
                    else{
                        if(parenthesisStack.isEmpty()){
                            throw new IllegalArgumentException();
                        }
                        directionStack.push(N);
                    }
                } else if (mine == ']') {
                    if (parenthesisStack.isEmpty()) {
                        throw new IllegalArgumentException();
                    } else if (parenthesisStack.pop() != '[') {
                        throw new IllegalArgumentException();
                    } else if (countStack.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    int n = countStack.pop();
                    if (directionStack.isEmpty()) {
                        if (result.isEmpty()) {
                            throw new IllegalArgumentException();
                        } else {
                            String s = "";
                            int m = result.getSize();
                            for (int a = 0; a<m; a++) {
                                Direction own = result.pop();
                                if (own.equals(Direction.EAST)) {
                                    s += "E";
                                } else if (own.equals(Direction.SOUTH)) {
                                    s += "S";
                                } else if (own.equals(Direction.NORTH)) {
                                    s += "N";
                                } else {
                                    s += "W";
                                }
                            }
                            s = reverseString(s);
                            String finalString = "";
                            for (int h = 0; h < n; h++) {
                                finalString += s;}
                            for (int a = 0; a < finalString.length(); a++) {
                                if (finalString.charAt(a)==(69)){
                                    result.push(Direction.EAST);
                                }else if (finalString.charAt(a)==83){
                                    result.push(Direction.SOUTH);
                                }else if (finalString.charAt(a)==87){
                                    result.push(Direction.WEST);
                                }else if (finalString.charAt(a)==78){
                                    result.push(Direction.NORTH);
                                }
                            }
                        }
                    } else {
                        String s = "";
                        int m = directionStack.getSize();
                        for (int a = 0; a<m; a++){
                            Direction own = directionStack.pop();
                            if (own.equals(Direction.EAST)){
                                s += "E";
                            }
                            else if(own.equals(Direction.SOUTH)){
                                s += "S";
                            }
                            else if(own.equals(Direction.NORTH)){
                                s += "N";
                            }
                            else{
                                s += "W";
                            }
                        }
                        s = reverseString(s);
                        String finalString = "";
                        for (int h = 0; h < n; h++) {
                            finalString += s;
                        }
                        for (int a = 0; a < finalString.length(); a++) {
                            if (finalString.charAt(a)==(69)){
                                result.push(Direction.EAST);
                            }else if (finalString.charAt(a)==83){
                                result.push(Direction.SOUTH);
                            }else if (finalString.charAt(a)==87){
                                result.push(Direction.WEST);
                            }else if (finalString.charAt(a)==78){
                                result.push(Direction.NORTH);
                            }
                        }
                    }
                }
                else {
                    throw new IllegalArgumentException("Syntax Error: Unknown Character");
                }
            }
            int z = result.getSize();
            for (int m = 0; m < z; m++) {
                michael.push(result.pop());
            }
            for (int n = 0; n<z;n++){
                super.enqueue(michael.pop());
            }
        }
    }


    private boolean checkOnly(String string){
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)==69 || string.charAt(i)==83 || string.charAt(i)==87 || string.charAt(i)==78) {
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    private String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
}
