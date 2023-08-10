package tn.spring.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.Services.Interfaces.IServiceAlram;
import tn.spring.springboot.Services.Interfaces.IServiceCredential;
import tn.spring.springboot.Services.Interfaces.IServiceDevice;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Alarm;
import tn.spring.springboot.entities.Device;

import java.util.concurrent.atomic.AtomicBoolean;

@RequestMapping("/device")
@RestController
public class DeviceController {

    @Autowired
    IServiceDevice iServiceDevice ;
    @Autowired
    IServiceAlram iServiceAlram ;
    @Autowired
    IServiceUser serviceUser ;

    @Autowired
    IServiceCredential iServiceCredential ;





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
    @GetMapping("/retrieveAllByUser/{offset}/{pageSize}")
    Page<Device> retrieveAllByUser(@RequestHeader("AUTHORIZATION") String header , @PathVariable int offset , @PathVariable int pageSize){
        String username = serviceUser.getusernamefromtoken(header);
        return  iServiceDevice.findAllByUserUsername(username , offset , pageSize);
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Device device, @RequestHeader("AUTHORIZATION") String header) {

        boolean exist = false;
        String username = serviceUser.getusernamefromtoken(header);
        //  if(iServiceAlram.existbyName())
        int i = 0;
        while (i < device.getAlarms().size() && !exist) {
            Alarm alarm = device.getAlarms().get(i);
            if (iServiceAlram.existbyName(alarm.getName())) {
                exist = true;
            }
            i++;
        }

        if(iServiceCredential.existbyName(device.getCredential().getUsername()))     {
            exist =true ;
        }
            if (exist == false) {
                Device addedDevice = iServiceDevice.addDevice(username, device);
                return new ResponseEntity<>(addedDevice, HttpStatus.CREATED);
            } else return new ResponseEntity<>("alarms name is already existing " , HttpStatus.BAD_REQUEST);



    }


    @GetMapping("/findDevicesByModelAndUser/{model}/{offset}/{pageSize}")
    Page<Device> findDevicesByModelAndUser(@RequestHeader("AUTHORIZATION") String header , @PathVariable int offset , @PathVariable int pageSize , @PathVariable String model){
        String username = serviceUser.getusernamefromtoken(header);
        return  iServiceDevice.findDevicesByModelAndUser(username , model , offset , pageSize);
    }


    @GetMapping("/findDevicesById/{id}")
    Device findDevicesById(@PathVariable int id){
        return  iServiceDevice.findById(id);
    }
}
