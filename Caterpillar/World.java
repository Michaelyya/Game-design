package Assignment2;


public class World {
    private Caterpillar caterpillar;
    private Position foodPosition;
    private Region map;
    private ActionQueue actionQueue;
    private TargetQueue targetQueue;
    private GameState gameState;

    public World(TargetQueue t, ActionQueue a){
        this.targetQueue = t;
        this.actionQueue = a;
        this.map = new Region(0,0,15,15);
        this.caterpillar = new Caterpillar();
        this.foodPosition = targetQueue.dequeue();
        this.gameState = GameState.MOVE;
    }

    public void step() {
        if (actionQueue.isEmpty()) {
            this.gameState = GameState.NO_MORE_ACTION;
            return;
        }
        Direction heading = actionQueue.dequeue();


        if (gameState != GameState.MOVE && gameState != GameState.EAT) {
            return;
        }

        Position currentPosition = new Position(caterpillar.getHead());

        Position nextPosition = new Position(currentPosition.getX(), currentPosition.getY());
        switch (heading) {
            case NORTH:
                nextPosition.moveNorth();
                break;
            case EAST:
                nextPosition.moveEast();
                break;
            case SOUTH:
                nextPosition.moveSouth();
                break;
            case WEST:
                nextPosition.moveWest();
                break;
        }
        if (!map.contains(nextPosition)) {
            this.gameState = GameState.WALL_COLLISION;
        } else if (caterpillar.selfCollision(nextPosition)) {
            this.gameState = GameState.SELF_COLLISION;
        } else if (foodPosition.equals(nextPosition)) {
            this.caterpillar.eat(nextPosition);
            if (targetQueue.isEmpty()) {
                gameState = GameState.DONE;
            } else {
                Position nextFood = targetQueue.dequeue();
                this.gameState = GameState.EAT;
            }
        } else {
            caterpillar.move(nextPosition);
            this.gameState = GameState.MOVE;
        }
    }

    public GameState getState(){
        return this.gameState;
    }

    public Caterpillar getCaterpillar(){
        return this.caterpillar;
    }

    public Position getFood(){
        return this.foodPosition;
    }

    public boolean isRunning(){
        return gameState == GameState.MOVE || gameState == GameState.EAT;
    }
}
