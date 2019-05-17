package net.lantrack.framework.sysbase.service.imp;

import net.lantrack.framework.core.service.SmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImp implements SmsService {

    @Override
    public Boolean sendSms(String phone, String cont) {
        // TODO Auto-generated method stub
        return true;
    }

}
