package dbriot.recovery;

import dbriot.database.Db;
import dbriot.database.DbWorld;
import difflib.DiffUtils;
import difflib.Patch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sean
 * Date: 10/19/11
 * Time: 11:20 PM
 */
public class GoogleDiffRecoveryStrategy implements RecoveryStrategy {

    @Override
    public boolean execute(DbWorld dbWorld) {
        List<Db> databases = dbWorld.getDatabases();
        for (Db db : databases) {
            db.online();
        }

        List<File> dumps = new ArrayList<File>();
        for (Db db : databases) {
            dumps.add((db.dump("/tmp/")));
        }

        for (File file : dumps) {
            for (File f : dumps) {
                if (f != file) {
                    Patch patch = DiffUtils.diff(fileToLines(f), fileToLines(file));
                    if(patch.getDeltas().size() > 0){
                        System.out.println("inspect the patch to see what could be applied");
                    }
                }
            }
        }


        boolean success = false;
        // TODO
        // maybe something like...
        // For each RECOVERING Db
        // Obtain some kind of dump from all other ONLINE Dbs from latest starting after latest record
        // Perform some kind of merge
        // Apply result to RECOVERING Db
        // Take Db online
        // Verify that all Dbs are now equiv by diffing the dump of each... or something
        // @see org.eclipse.jgit.merge.*
        // @see org.eclipse.jgit.diff.*
        return success;
    }


    private static List<String> fileToLines(File file) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


}
