class Bilhete{
    private int id,valor;
    private Sessao s;
    private Cliente c;
    public Bilhete(int id, int valor, Sessao s, Cliente c){
        this.id = id;
        this.valor = valor;
        this.s = s;
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Sessao getS() {
        return s;
    }

    public void setS(Sessao s) {
        this.s = s;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
    
    
}