package Assignment2;


public class TargetQueue extends MyQueue<Position>{
    private MyStack<String> myStack;

    public TargetQueue(){
        super();
        myStack = new MyStack<>();
    }

    @Override
    public void clear() {
        myStack.clear();
        super.clear();
    }

    public void addTargets(String string) throws IllegalArgumentException{
        String num = new String();

        for(int i = 0; i< string.length(); i++){
            char mine = string.charAt(i);

            if (mine == '('){
                if (!myStack.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException();}
                myStack.push(String.valueOf('('));
            }

            else if(Character.isDigit(mine)){
                num += mine;
                if (i == string.length()-1){
                    throw new IllegalArgumentException();
                }
            }
            else if(mine == ','){
                if (num.isEmpty()){
                    throw new IllegalArgumentException();
                }
                else if (i == string.length()-1){
                    throw new IllegalArgumentException();
                }
                try {
                    int x = Integer.parseInt(num);
                    myStack.push(num);
                    myStack.push(String.valueOf(','));
                    num = "";
                }
                catch (NumberFormatException X){
                    throw new IllegalArgumentException();
                }
            }

            else if (mine == ')') {
                if (num.isEmpty() || myStack.getSize() < 3) {
                    throw new IllegalArgumentException();
                }
                int yIntercept = Integer.parseInt(num);
                try {
                    if ((!myStack.pop().equals(","))) {
                        throw new IllegalArgumentException();
                    }
                    int xIntercept = Integer.parseInt(myStack.pop());
                    myStack.pop();
                    super.enqueue(new Position(xIntercept, yIntercept));
                    num = "";
                } catch (NumberFormatException Y) {
                    throw new IllegalArgumentException();
                }
                if (i<string.length()-1 && string.charAt(i+1) != '.'){
                    throw new IllegalArgumentException();
                }
            }
            else if(mine == '.'){
                if (!myStack.isEmpty() || !num.isEmpty()){
                    throw new IllegalArgumentException();
                }
                else if (i<string.length()-1 && string.charAt(i+1) == '.'){
                    throw new IllegalArgumentException();
                }
            }
            else{
                throw new IllegalArgumentException();
            }
        }
    }
}








