public Tablero {
    private int sizeX, sizeY;
    private String[][] mapa;
    ArrayList<Enemigo> enemigos;
    ArrayList<Disparo> disparos;

    public Tablero(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public Tablero(){

    }
    public void setSizeX(int sizeX){
        this.sizeX = sizeX;
    }
    public void setSizeY(int sizeY){
        this.sizeY = sizeY;
    }
    public void spawnEnemigo(){
        Enemigo enemy = new Enemigo();
        enemy.setX(0);
        enemy.setY(Math.random()*10);
        enemigos.add(enemy);
    }
    public void destruirEnemigos(Enemigo enemy){
        enemigos.remove(enemy);
    }
    public void llenarMapaVacio(){
        for(int i=0; i<mapa.length;i++){
            for(int j=0 ; j<mapa[i].length;j++){
                if(){
                    System.out.printf(" \t",);
                }       
            }
            System.out.print("\n");
        }
    }
    public void imprimir(String[] mapa){
        for(int i=0; i<mapa.length;i++){
            for(int j=0 ; j<mapa[i].length;j++){
                System.out.printf("%s\t", mapa[i][j]);
            }
            System.out.print("\n");
        }
    }

}