import java.util.Date;
class Sessao{
    private Filme f;
    private int id,sala;
    private Date horario;
    public Sessao(Filme f, Date horario, int id, int sala){
        this.f = f;
        this.horario = horario;
        this.id = id;
        this.sala = sala;
    }

    public Filme getF() {
        return f;
    }

    public void setF(Filme f) {
        this.f = f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }
    
}