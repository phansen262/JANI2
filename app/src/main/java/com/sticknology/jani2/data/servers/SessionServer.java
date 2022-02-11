package com.sticknology.jani2.data.servers;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.base_operations.FileProxy;
import com.sticknology.jani2.data.UserFileName;
import com.sticknology.jani2.data.doms.SessionDOM;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionServer {

    public enum SessionGroup{
        ALL;
    }

    public static void saveSession(Session session, Context context){

        SessionDOM.writeSession(context, session);
        appendSessionPath(session.getPath(), context);
    }

    public static List<Session> getSessionList(Context context){

        List<Session> retList = new ArrayList<>();

        System.out.println("This is size of getSessionPaths:  " + getSessionPaths(SessionGroup.ALL, context));

        for(String path : getSessionPaths(SessionGroup.ALL, context)){
            System.out.println(path + ":  This is path");
            retList.add(SessionDOM.readSession(context, path));
        }

        return retList;
    }

    private static List<String> getSessionPaths(SessionGroup sessionGroup, Context context){

        List<String> retList = new ArrayList<>();
        if(sessionGroup == SessionGroup.ALL){
            retList = new FileProxy().readFileLines(UserFileName.SESSION_REGISTRY.getPath(), context);
            System.out.println(retList.size() + " : this is ret list size");
        }

        return retList;
    }

    private static void appendSessionPath(String path, Context c){
        FileProxy fileProxy = new FileProxy();
        fileProxy.appendText(path, UserFileName.SESSION_REGISTRY.getPath(), c);
    }
}
