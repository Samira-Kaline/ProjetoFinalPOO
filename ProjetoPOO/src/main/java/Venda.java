import java.util.Date;
class Venda{
    private int id,valor;
    private Date data;
    private Cliente c;
    public Venda(int id, int valor, Date data, Cliente c){
        this.id = id;
        this.valor = valor;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
    
}