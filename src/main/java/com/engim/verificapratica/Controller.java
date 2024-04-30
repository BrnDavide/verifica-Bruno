package com.engim.verificapratica;

import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private static Sorteggio sorteggio = new Sorteggio();


    /*
    * ES 1: get ("/add?nome=n&nazione=m") che aggiunge al sorteggio una squadra con nome n e nazione m (utilizzare
    * la classe Sorteggio) - 15 p*/

    @GetMapping("/addNomeNazione")
            public void aggiungiNomeNazione(@RequestParam(value="nome")String nome,
                                              @RequestParam(value="nazione")String nazione)
    {
        sorteggio.aggiungi("AllBlacks", "NewZealand");
    }

    /*
    * ES 2: get ("/listaSquadre?nazione=n") che restituisce la lista delle squadre di nazione n - 20 p*/

    @GetMapping("/squadre")
        public List<String> listaSquadre(@RequestParam(value="nazione")String nazione) {
        List<String> squadreStessaNazione = new ArrayList<>();
        List<Squadra> squadre = sorteggio.getSquadre();
        for (int i = 0; i < squadre.size(); i++) {
            if (squadre.get(i).getNazione().equals(nazione)) {
                squadreStessaNazione.add(squadre.get(i).getNome());
            }
        }
        return squadreStessaNazione;
    }
    /* ES 3: get ("/sorteggia") che restituisce 2 squadre di nazioni diverse (utilizzare la classe Sorteggio, il metodo
    * next. Consiglio: dopo aver sorteggiato la prima, continuare a sorteggiare finché la seconda
    * non è di una nazione diversa) - 20 p*/

        @GetMapping("/sorteggia")
        public List<Squadra> sorteggia(){

        Squadra s1 = sorteggio.next();
        Squadra s2 = sorteggio.next();
        while(s1.getNazione().equals(s2.getNazione()))
            s2 = sorteggio.next();

        List<Squadra> selezionate = new ArrayList<>();
        selezionate.add(s1);
        selezionate.add(s2);

        return selezionate;
    }

    /* ES 4: implementare il sorteggio delle squadre di una fase finale di un torneo a eliminazione diretta.
    * Creare il metodo sorteggiaPartite che:
    * - controlla se il numero di squadre aggiunte è una potenza di 2. Se non lo è lancia una RuntimeException.
    * - Finché ci sono squadre non sorteggiate: sorteggia 2 squadre e le inserisce in un oggetto della classe Partita (da creare)
    * - restituisce la lista di Partite.
    * creare get ("/getPartite") che restituisce la lista appena creata - 30 p
    * */

    @GetMapping("/getPartite")
    public List<Partita> sorteggiaPartite()
    {
        List<Squadra> squadre = sorteggio.getSquadre();

                if (Math.sqrt(squadre.size()) != 0) {
                    throw new RuntimeException("Numero squadre non sufficiente");
                }//end if
                else {
                    while (!squadre.isEmpty()) {
                        List<Partita> coppiaDiSquadre = new ArrayList<>();

                        Squadra s1 = sorteggio.next();
                        Squadra s2 = sorteggio.next();
                        coppiaDiSquadre.add((Partita) s1);
                        coppiaDiSquadre.add((Partita) s2);

                    }//end while
                }//end else
        return coppiaDiSquadre;
    }//end method

}//end controller
