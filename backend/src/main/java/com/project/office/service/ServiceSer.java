package com.project.office.service;

import com.project.office.entity.Service;

import java.util.List;

public interface ServiceSer {
    Service createService(Long team_id,Service service);
    Service updateService(Service service);
    List<Service> findAllService(Long team_id);
    Boolean deleteService(Long id);

}
