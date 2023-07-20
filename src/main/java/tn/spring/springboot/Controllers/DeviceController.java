package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.springboot.Services.Interfaces.IServiceDevice;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.Role;
import tn.spring.springboot.entities.User;

import java.net.URI;

@RequestMapping("/device")
@RestController
public class DeviceController {

    @Autowired
    IServiceDevice iServiceDevice ;


    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Device> addRole(@RequestBody Device device ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/device/add").toUriString());
        return ResponseEntity.created(uri).body(iServiceDevice.addDevice(device));
    }


    @GetMapping(value = "/getAllDevices/{offset}/{pageSize}")
    @ResponseBody
    public Page<Device> getAllDevices(@PathVariable int offset , @PathVariable int pageSize) {
        return iServiceDevice.getAllDevices(offset, pageSize);
    }

    @GetMapping(value = "/findById/{id}")
    @ResponseBody
    public Device findById(@PathVariable int id) {
        return iServiceDevice.findById(id);
    }



    @PatchMapping("/update/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable int id, @RequestBody Device device) {
        Device updatedDevice = iServiceDevice.updateDevice(id, device);
        return ResponseEntity.ok(updatedDevice);
    }


}
