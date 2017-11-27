/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import asistencia.IAsistencia;
import dto.Decanato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josera
 */
@Controller
public class CDecanato {
    private IAsistencia asistencia;
    
    @Autowired(required = true)
    @Qualifier(value="asistencia")
    public void setAsistencia(IAsistencia as) {
        this.asistencia = as;
    }
    
    @RequestMapping(value = "/decanatos", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("decanato", new Decanato());
        model.addAttribute("listDecanatos", this.asistencia.listDecanatos());
        return "decanto";
    }
    
    //For add and update decanato both
    @RequestMapping(value= "/decanato/add", method = RequestMethod.POST)
    public String addDecanato(@ModelAttribute("decanato") Decanato d) throws Exception{
        this.asistencia.guardarDecanato(d);
        return "redirect:/persons";
    }
}
