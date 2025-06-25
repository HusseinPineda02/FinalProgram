
/**
 * @author FrankUba
 */
public class Enemigo {
    
   
    private String tipo;
    private int salud;
    private int ataque;

    public Enemigo(String tipo, int salud, int ataque) {
        this.tipo = tipo;
        this.salud = salud;
        this.ataque = ataque;
    }
    
    public void atacar (Nave jugador){ //atacan al jugador y se reduce la salud.
        jugador.recibirDaño(ataque);
    }
    public void recibirDaño(int cantidad){ //resta la ssalud del enemigo
        salud-=cantidad;
        if (salud<0)salud=0;
    }
    public boolean estarVivo(){ //retorna true si el enemigo tiene la salud mayor de 0
        return salud>0;
    }
    public void mostrarEstado(){ 
        System.out.println("enemigo tipo: "+tipo+"tiene: "+salud+"de salud.");
    }
}
