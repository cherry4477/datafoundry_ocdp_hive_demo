package com.asiainfo.bdx.ocdp;

/**
 * Created by baikai on 11/4/16.
 */
public class HiveTest {
    public static void main(String[] args){
        if (args.length != 6){
            System.out.println("Args number not match, need 6 parameters: \n " +
                    "1) hive host \n " +
                    "2) hive port \n " +
                    "3) hive user principal \n " +
                    "4) hive sql \n " +
                    "5) sql type (execute|query) \n " +
                    "6) hive sql ");
            return;
        }
        System.out.println("Hive host is: " + args[0]);
        System.out.println("Hive port is: " + args[1]);
        System.out.println("Hive db name is: " + args[2]);
        System.out.println("Hive user principal is: " + args[3]);
        System.out.println("Hive SQL is: " + args[5]);
        HiveClient client = new HiveClient(args[0], args[1], args[2], args[3]);
        try
        {
            if (args[4].equals("execute")){
                client.execute(args[5]);
            }
            else if (args[4].equals("query")){
                client.queryExecute(args[5]);
            }
            else{
                System.out.println("Please enter correct sql type: execute|query");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
