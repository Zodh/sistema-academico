package br.com.unip.sistema_academico.modelo.Rendimento;

import br.com.unip.sistema_academico.modelo.Nota;
import br.com.unip.sistema_academico.modelo.Notas;

public class RendimentoGraduacao extends Rendimento{

    public RendimentoGraduacao(String id, Notas aNotas) {
        super(id, aNotas);
    }

    @Override
    public Nota getMedia() {
        double media;
        if (getNotas().getReposicao().getNota() == 0){
            media = (getNotas().getNp1().getNota() + getNotas().getNp2().getNota()) / 2;
        } else {
            if (getNotas().getNp1().getNota() > getNotas().getReposicao().getNota() && getNotas().getNp2().getNota() > getNotas().getReposicao().getNota()) {
                media = (getNotas().getNp1().getNota() + getNotas().getNp2().getNota()) / 2;
            } else if (getNotas().getReposicao().getNota() > getNotas().getNp1().getNota() && getNotas().getNp2().getNota() > getNotas().getNp1().getNota()) {
                media = (getNotas().getReposicao().getNota() + getNotas().getNp2().getNota()) / 2;
            } else {
                media = (getNotas().getNp1().getNota() + getNotas().getReposicao().getNota()) / 2;
            }
        }
        if (media < 7){
            return new Nota((media + getNotas().getExame().getNota())/2);
        }
        return new Nota(media);
    }
}
