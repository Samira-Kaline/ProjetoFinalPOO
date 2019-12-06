class ItensVenda{
    private int qtd;
    private Produto p;
    private Venda v;
    public ItensVenda(int qtd, Produto p, Venda v){
        this.qtd = qtd;
        this.p = p;
        this.v =v;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public Venda getV() {
        return v;
    }

    public void setV(Venda v) {
        this.v = v;
    }
}