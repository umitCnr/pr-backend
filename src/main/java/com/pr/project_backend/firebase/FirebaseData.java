package com.pr.project_backend.firebase;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FirebaseData {

    int id;

    String name;

    String description;

    String status;

    List<FireBaseFriendlyData> friendlyData;

}
