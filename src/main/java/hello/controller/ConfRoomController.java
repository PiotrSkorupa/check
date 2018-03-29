package hello.controller;


import hello.model.ConfRoomModel;
import hello.service.ConfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/conf")
public class ConfRoomController {


    @Autowired
    private ConfRoomService confRoomService;

    @GetMapping("/list")
    public String listCustomers(Model model){
        List<ConfRoomModel> customers = confRoomService.getAll();
        model.addAttribute("confRoomModel", customers);
        return "confRoomList";
    }

    @GetMapping("/saveConf")
    public String addNewConfRoom(Model model){
        ConfRoomModel confRoomModel = new ConfRoomModel();
        model.addAttribute("confRoom",confRoomModel);
        return "save-confRoom";
    }

    @PostMapping("save")
    public String saveConfRoom(@ModelAttribute("confRoom") ConfRoomModel confRoomModel){
        confRoomService.saveConfRoom(confRoomModel);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    public String deleteConfRoom(@RequestParam("confRoomId") int id){
        confRoomService.deleteConfRoom(id);
        return "redirect:/list";
    }

    @GetMapping("/update")
    public String updateCOnfRoom(@RequestParam("confRoomId") int id, Model model){
        Optional<ConfRoomModel> confRoomModel = confRoomService.getConfRoom(id);
        model.addAttribute("confRoom",confRoomModel);
        return "save-confRoom";
    }

}