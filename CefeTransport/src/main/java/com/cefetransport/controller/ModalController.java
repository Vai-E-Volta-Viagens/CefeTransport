package com.cefetransport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cefetransport.exception.ModalExistRegistroException;
import com.cefetransport.exception.ModalNoExistException;
import com.cefetransport.exception.UsuarioNaoLogadoException;
import com.cefetransport.model.Modal;
import com.cefetransport.repository.ModalRepository;
import com.cefetransport.service.AtividadeService;
import com.cefetransport.service.ModalService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/modal")
public class ModalController {
    
    ModelAndView mv = new ModelAndView();

    @Autowired
    private ModalService modalService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ModalRepository modalRepository;

    @Autowired
    private FuncionarioController funcionarioController;

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/cadastrar")
    public ModelAndView paginaCadastrarModal(HttpSession session) throws UsuarioNaoLogadoException {

        if (!(session.getAttribute("funcionarioLogado") != null)) {

            throw new UsuarioNaoLogadoException();

        }

        mv.addObject("modal", new Modal());
        mv.setViewName("home/cadastrarModal");

        return mv;

    }

    @PostMapping("/cadastrarModal")
    public String cadastrarModal(@Valid Modal modal, BindingResult br, HttpSession session, Model model) throws Exception, UsuarioNaoLogadoException {
        
        if (!(session.getAttribute("funcionarioLogado") != null)) {

            throw new UsuarioNaoLogadoException();

        }
        
        mv.addObject("modal", new Modal());

        if (br.hasErrors()) {

            return "home/cadastrarModal";

        } else {

            try {
                
                Modal modalSalvo = modalService.cadastrarModal(modal);

                atividadeService.salvarAtividade(funcionarioController.getEntidadeLogado(), modalSalvo,
                        "Cadastro de Modal");

                return "redirect:/modal/";

            } catch (ModalExistRegistroException e) {
                
                model.addAttribute("erro", e.getMessage());
                return "home/cadastrarModal";

            }

        }

    }
    
    @GetMapping("/")
    public ModelAndView modaisCadastrados(HttpSession session) throws UsuarioNaoLogadoException {

        if (!(session.getAttribute("funcionarioLogado") != null)) {

            throw new UsuarioNaoLogadoException();

        }

        mv.addObject("modalList", modalRepository.findAll());
        mv.setViewName("home/modaisCadastrados");

        return mv;

    }

    @GetMapping("/alterar/{id}")
    public ModelAndView paginaAlterarModal(HttpSession session, @PathVariable("id") Long id, Modal modal)
            throws UsuarioNaoLogadoException {

        if (!(session.getAttribute("funcionarioLogado") != null)) {

            throw new UsuarioNaoLogadoException();

        }

        mv.addObject("modal", modalService.buscarModalPorId(id));
        mv.setViewName("home/alterarModal");

        return mv;

    }
    
    @Transactional
    @PostMapping("/alterarModal")
    public String alterarModal(@Valid Modal modal, BindingResult br, Model model) {

        if (br.hasErrors()) {

            return "home/alterarModal";

        } else {

            try {
                
                modalService.alterarModal(modal);

                Modal modalAtual = entityManager.merge(modal);

                atividadeService.salvarAtividade(funcionarioController.getEntidadeLogado(), modalAtual,
                        "Alteração de Modal");

                return "redirect:/modal/";

            } catch (ModalNoExistException e) {
                
                model.addAttribute("erro", e.getMessage());

                return "home/modaisCadastrados";

            }

        }

    }

    @ExceptionHandler(UsuarioNaoLogadoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUsuarioNaoLogadoException() {

        return "exception/exception";

    }

    // Para conexão com o sistema principal

    @GetMapping("/modais")
    public ResponseEntity<List<Modal>> modaisCadastradosParaConexaoApi() {

        List<Modal> modais = modalRepository.findAll();
        return ResponseEntity.ok(modais);

    }

    @GetMapping("/registrosmodais")
    public ResponseEntity<List<String>> registrosModais() {

        List<String> registros = modalRepository.buscarRegistrosModais();

        return ResponseEntity.ok(registros);

    }

    @GetMapping("/vagasmodais")
    public ResponseEntity<List<Integer>> vagasModais() {

        List<Integer> vagas = modalRepository.buscarVagasModais();

        return ResponseEntity.ok(vagas);

    }

    @GetMapping("/verificastatus")
    public ResponseEntity<String> verificaStatusModalPorRegistro() {

        String statusVerificado = modalRepository.buscarStatusDoModalPorRegistro("ABC-1D23");

        return ResponseEntity.ok(statusVerificado);

    }

}