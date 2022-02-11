package com.sticknology.jani2.data.servers;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.base_objects.MDay;
import com.sticknology.jani2.base_operations.DirectoryProxy;
import com.sticknology.jani2.base_operations.FileProxy;
import com.sticknology.jani2.data.DirectoryNames;
import com.sticknology.jani2.data.UserFileName;
import com.sticknology.jani2.data.doms.SessionDOM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;

public class SessionServer {

    public enum SessionGroup{
        ALL;
    }

    public static void saveSession(Session session, File f, Context c){

        SessionDOM.writeSession(session, f);
        appendSessionPath(session.getPath(), c);
    }

    public static List<Session> getSessionList(Context c){

        List<Session> retList = new ArrayList<>();
        File dir = new File(c.getFilesDir(), DirectoryNames.SESSION_LIST.toString());

        for(File f : Objects.requireNonNull(dir.listFiles())){
            retList.add(SessionDOM.readSession(f));
        }

        return retList;
    }

    public static void writeAssignedSession(Session session, MDay day, Context c){

        File dir = getAssignedSessionDir(day, c);
        File f = new File(dir, day.getDayInMonth() + "@!@" + session.getName() + ".xml");
        f.getParentFile().mkdirs();
        SessionDOM.writeSession(session, f);
    }

    public static List<Session> getAssignedSessionList(MDay day, Context context){

        List<Session> retList = new ArrayList<>();
        File dir = getAssignedSessionDir(day, context);

        //Break out in case end directory does not exist
        if(dir.listFiles() == null){return retList;}

        //Proceed to get sessions from found directory
        for(File f : Objects.requireNonNull(dir.listFiles())){
            if(f.getPath().split("@!@")[0].equals(String.valueOf(day.getDay()))){
                retList.add(SessionDOM.readSession(f));
            }
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

    private static File getAssignedSessionDir(MDay mDay, Context c){
        List<String> dirNames = Arrays.asList(DirectoryNames.SESSION_ASSIGNED.toString(),
                String.valueOf(mDay.getYear()),
                String.valueOf(mDay.getMonth()));
        return new DirectoryProxy().getEndDirectory(dirNames, c);
    }
}
