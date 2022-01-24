package com.sticknology.jani2.data;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.app_objects.trainingplan.sessions.SessionDOM;

import java.util.Collections;
import java.util.List;

public class SessionServer {

    public static void saveSession(Session session, Context context){

        SessionDOM.writeSession(context, session);
    }

    public static List<Session> getSessionList(Context context){

        return Collections.singletonList(SessionDOM.readSession(context, getSessionPaths().get(0)));
    }

    private static List<String> getSessionPaths(){

        return Collections.singletonList("session_test.xml");
    }
}
