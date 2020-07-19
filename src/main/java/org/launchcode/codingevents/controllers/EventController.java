package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("events")
public class EventController {

//    private static List<Event> events = new ArrayList<>();

    @GetMapping("test-hashmap")
    public String displayHashMapEvents(Model model) {
        model.addAttribute("events-hashmap", EventData.events);
//        model.addAttribute("love", "I love you Miley!");

        return "/events/testhashmap";
    }

    @GetMapping()
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "Display All Events");
        model.addAttribute("events", EventData.getAll());
//        model.addAttribute("love", "I love you Miley!");

        return "/events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "/events/create";
    }
/*
    @PostMapping("processCreate")
//    We can not use @PathVariable for the parameter here
    public String processCreateEventForm(@RequestParam String eventName,
                                         @RequestParam String eventDescription,
                                         Model model) {
        //judge the eventName if empty and duplicate(case-insensitive)
        if (eventName != "") {
//            check the string eventName
//            ArrayList<String> checkList=new ArrayList<>();
//            for (int i = 0; i < events.size(); i++) {
//                checkList.add(events.get(i).getName().toLowerCase());
//            }
//            if(!checkList.contains(eventName.toLowerCase())){
//                events.add(new Event(eventName,eventDescription));
//            }
            //check the int id
//            ArrayList<Integer> checkList=new ArrayList<>();
//            Event newEvent=new Event(eventName,eventDescription);
//            for (int i = 0; i < events.size(); i++) {
//                checkList.add(events.get(i).getId());
//            }
//            if(!checkList.contains(newEvent.getId())){
//                EventData.add(newEvent);
//            }
            EventData.add(new Event(eventName, eventDescription));
        }
        //We cannot transfer the value of variable "love" to /events/index.html
//        model.addAttribute("love", "I love you Miley!");
        return "redirect:";
        //to the root page of this controller,it is /events,it will display /events/index.html
    }*/

    @PostMapping("processCreateModelBinding")
    public String processCreateEventFormModelBinding(@ModelAttribute Event newEvent) {
//        check if name is empty?
        if(newEvent.getName()!=""){
            EventData.add(newEvent);
        }
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";

    }



    @PostMapping("processDelete")
    public String processDeleteForm(@RequestParam(required = false) List<Integer> deleteIds) {
        if (deleteIds != null) {
            for (int id : deleteIds) {
                if (EventData.getById(id) != null) {
                    EventData.remove(id);
                }
            }
        }
        return "redirect:";
    }

}
