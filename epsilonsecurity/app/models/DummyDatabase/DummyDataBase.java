package models.DummyDatabase;

import models.databaseModel.helpers.*;
import models.databaseModel.roles.*;
import models.databaseModel.scheduling.*;
import models.queries.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class DummyDataBase {

    private List<DbRole> roleList = new ArrayList<>();
    private List<DbUser> userList = new ArrayList<>();
    private List<DbTeam> teamList = new ArrayList<>();
    private List<DbShift> shiftList = new ArrayList<>();
    private List<DbShiftType> shiftTypeList = new ArrayList<>();
    private List<DbQualification> qualificationList = new ArrayList<>();



    private List<DbPermission> permissionList = new ArrayList<>();
    private List<DbRolePermission> rolePermissionList = new ArrayList<>();
    private List<DbUserTeam> userTeamList = new ArrayList<>();
    private List<DbUserQualification> userQualificationList = new ArrayList<>();
    private List<DbShiftQualification> shiftQualificationList = new ArrayList<>();
    private List<DbUserShift> userShiftList= new ArrayList<>();
    private List<DbOneTimeAvailability> oneTimeAvailList = new ArrayList<>();



    private void initAllLists() {
        initRoles();
        initUsers();
        initPermission();
        initTeam();
        initQualification();
        initShiftType();

//        initRolePermission();
//        initUserTeam();
//        initUserQualification();
//        initShiftAndUserShift();
//        initOneTimeAvailAndUserShift();
    }


    public DummyDataBase() {
        //init dummy names
        initAllLists();
        //generateDatabase();

        debugPrintLists();
    }


    //prints all listss
    private void debugPrintLists() {

        initRoles();
        initUsers();
        initPermission();
        initTeam();
        initQualification();
        initShiftType();


        //creates roles
        System.out.println("Role List:");
        System.out.println("================================================");
        for(DbRole itr: roleList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");


        //creates users
        System.out.println("User List:");
        System.out.println("================================================");
        for(DbUser itr: userList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");


        //creates permission
        System.out.println("Permission List:");
        System.out.println("================================================");
        for(DbPermission itr: permissionList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");


        //creates team
        System.out.println("Team List:");
        System.out.println("================================================");
        for(DbTeam itr: teamList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");


        //creates qualifcations
        System.out.println("Qualification List:");
        System.out.println("================================================");
        for(DbQualification itr: qualificationList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");


        //creates shiftType
        System.out.println("ShiftType List:");
        System.out.println("================================================");
        for(DbShiftType itr: shiftTypeList) {
            System.out.println(itr.toString());
        }
        System.out.println("------------------------------------------------\n");



//        //links rolepermission
//        System.out.println("RolePermission List:");
//        System.out.println("================================================");
//        for(DbRolePermission itr: rolePermissionList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");

//        //links userTeam
//        System.out.println("UserTeam List:");
//        System.out.println("================================================");
//        for(DbUserTeam itr: userTeamList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");
//
//        //links shift qualifcation
//        System.out.println("ShiftQualification List:");
//        System.out.println("================================================");
//        for(DbShiftQualification itr: shiftQualificationList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");
//
//
//
//
//        //creates userQualification
//        System.out.println("UserQualification List:");
//        System.out.println("================================================");
//        for(DbUserQualification itr: userQualificationList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");


//
//        //creates Shift
//        System.out.println("Shift List:");
//        System.out.println("================================================");
//        for(DbShift itr: shiftList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");
//
//        //creates one time availability
//        System.out.println("OneTimeAvail List:");
//        System.out.println("================================================");
//        for(DbOneTimeAvailability itr: oneTimeAvailList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");
//
//        //creates userShift
//        System.out.println("UserShift List:");
//        System.out.println("================================================");
//        for(DbUserShift itr: userShiftList) {
//            System.out.println(itr.toString());
//        }
//        System.out.println("------------------------------------------------\n");
    }




    private void generateDatabase() {
        //creates roles
        for(DbRole itr: roleList) {
            DbRoleHelper.createDbRole(itr);
        }

        //creates users
        for(DbUser itr: userList) {
            DbUserHelper.createDbUser(itr);
        }

        //creates team
        for(DbTeam itr: teamList) {
            DbTeamHelper.createDbTeam(itr);
        }

        //creates permission
        for(DbPermission itr: permissionList) {
            DbPermissionHelper.createDbPermission(itr);
        }

        //creates shiftType
        for(DbShiftType itr: shiftTypeList) {
            DbShiftTypeHelper.createDbShiftType(itr);
        }


        //creates qualifcations
        for(DbQualification itr: qualificationList) {
            DbQualificationHelper.createDbQualification(itr);
        }


        

        //links rolepermission
        for(DbRolePermission itr: rolePermissionList) {
            DbRolePermissionHelper.createDbRolePermission(itr);
        }

        //links userTeam
        for(DbUserTeam itr: userTeamList) {
            DbUserTeamHelper.createDbUserTeam(itr);
        }

        //links shift qualifcation
        for(DbShiftQualification itr: shiftQualificationList) {
            DbShiftQualificationHelper.createDbShiftQualification(itr);
        }


        //creates userQualification
        for(DbUserQualification itr: userQualificationList) {
            DbUserQualificationHelper.createDbUserQualification(itr);
        }


        //creates Shift
        for(DbShift itr: shiftList) {
            DbShiftHelper.createDbShift(itr);
        }

        //creates one time availability
        for(DbOneTimeAvailability itr: oneTimeAvailList) {
            DbOneTimeAvailabilityHelper.createDbOneTimeAvailability(itr);
        }

        //creates userShift
        for(DbUserShift itr: userShiftList) {
            DbUserShiftHelper.createDbUserShift(itr);
        }
    }

    //20 items
    //INIT NON SINGLE RELATED TABLES
    private void initRoles() {
        roleList.add(new DbRole("admin"));
        roleList.add(new DbRole("volunteer"));
        roleList.add(new DbRole("employee"));
        roleList.add(new DbRole("teamlead"));
        roleList.add(new DbRole("supervisor"));
    }

    private void initUsers() {

        //Burnaby 8
        userList.add(new DbUser("Admin", "God", "admin@sfu.ca", "example@email.ca", "(846) 794-8623", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Gary", "Hernandez","hern@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Deborah", "Kelly", "kelly@sfu.ca", "example@email.ca", "(509)-499-0611", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Scott", "King", "king@sfu.ca", "example@email.ca", "(553)-449-0771", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Larry", "Ross", "ross@sfu.ca", "example@email.ca", "(777)-685-2470", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Juan", "Roberts", "rob@sfu.ca", "example@email.ca", "(226)-765-6193", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Sandra", "Perez", "teamlead@sfu.ca", "example@email.ca", "(366)-920-4269", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Theresa", "Henderson", "supervisor@sfu.ca", "example@email.ca", "(123)-654-7878", "https://www.PhotoUrl.ca"));

        //Surrey 7
        userList.add(new DbUser("Mildred", "Adams", "adam@sfu.ca", "example@email.ca", "(135)-558-6155)", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Craig", "Turner", "turn@sfu.ca", "example@email.ca", "(880)-905-9377", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Emily", "Mitchell", "mitch@sfu.ca", "example@email.ca", "(136)-989-6296", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Virginia", "Hall", "hall@sfu.ca", "example@email.ca", "(656) 978-2274", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kenneth", "Sanders", "sand@sfu.ca", "example@email.ca", "(878) 111-5092", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Jeremy", "Wright", "teamlead@sfu.ca", "example@email.ca", "(730) 639-9920", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Elizabeth", "Williams", "supervisor@sfu.ca", "example@email.ca", "(484) 889-6654", "https://www.PhotoUrl.ca"));

        //Vancouver 5
        userList.add(new DbUser("Judy", "Rivera", "will@sfu.ca", "example@email.ca", " (462) 854-5281", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Kathryn", "Russell", "russ@sfu.ca", "example@email.ca", "(588) 618-6391", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Michael", "Coleman", "cole@sfu.ca", "example@email.ca", "(362) 101-6356", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("Bob", "Builder", "teamlead@sfu.ca", "example@email.ca", "(577) 732-7675", "https://www.PhotoUrl.ca"));
        userList.add(new DbUser("John", "Cena", "supervisor@sfu.ca", "example@email.ca", " (901) 552-2492", "https://www.PhotoUrl.ca"));
    }

    private void initPermission() {

        permissionList.add(new DbPermission("admin"));
        permissionList.add(new DbPermission("volunteer"));
        permissionList.add(new DbPermission("employee"));
        permissionList.add(new DbPermission("teamlead"));
        permissionList.add(new DbPermission("supervisor"));
    }

    private void initTeam() {
        teamList.add(new DbTeam("BURNABY"));
        teamList.add(new DbTeam("SURREY"));
        teamList.add(new DbTeam("VANCOUVER"));
    }

    private void initQualification() {

        qualificationList.add(new DbQualification("Customer Support Training"));
        qualificationList.add(new DbQualification("Transit traffic training"));
        qualificationList.add(new DbQualification("Security Training"));
        qualificationList.add(new DbQualification("Bike/Driver License Training"));
        qualificationList.add(new DbQualification("Manager Training"));
        qualificationList.add(new DbQualification("Common Hazard Training"));
    }

    private void initShiftType() {

        shiftTypeList.add(new DbShiftType("Information and Lost&Found Kiosk", ""));
        shiftTypeList.add(new DbShiftType("Speed Watch/Moving Traffic", ""));
        shiftTypeList.add(new DbShiftType("Community Presence", ""));
        shiftTypeList.add(new DbShiftType("Safety Screen", ""));
        shiftTypeList.add(new DbShiftType("Theft Prevention", ""));
        shiftTypeList.add(new DbShiftType("Auto Theft Prevention", ""));
        shiftTypeList.add(new DbShiftType("Bike Presence", ""));
        shiftTypeList.add(new DbShiftType("Special Events (peaceful protests, campus ceremonies, etc)", ""));
        shiftTypeList.add(new DbShiftType("Smoking Checks", ""));
        shiftTypeList.add(new DbShiftType("Pedestrian Safety", ""));
    }



    //INIT RELATED TABLES


    private void linkUserRole() {
        //set roleId
        int counter = 0;

        //burnaby
        //person 1
        userList.get(counter).setRoleId(roleList.get(0).getId());
        counter++;
        //person 2
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 3
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 4
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 5
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 6
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 7
        userList.get(counter).setRoleId(roleList.get(3).getId());
        counter++;
        //person 8
        userList.get(counter).setRoleId(roleList.get(4).getId());
        counter++;


        //vancouver
        //person 9
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 10
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 11
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 12
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 13
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 14
        userList.get(counter).setRoleId(roleList.get(3).getId());
        counter++;
        //person 15
        userList.get(counter).setRoleId(roleList.get(4).getId());
        counter++;


        //vancouver
        //person 16
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 17
        userList.get(counter).setRoleId(roleList.get(1).getId());
        counter++;
        //person 18
        userList.get(counter).setRoleId(roleList.get(2).getId());
        counter++;
        //person 19
        userList.get(counter).setRoleId(roleList.get(3).getId());
        counter++;
        //person 20
        userList.get(counter).setRoleId(roleList.get(4).getId());
        counter++;
    }

    private void initRolePermission() {

        //burnaby
        int counter = 0;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(0).getId(), AccessLevel.ADMIN));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(3).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(0).getId(), roleList.get(counter).getId(), permissionList.get(4).getId(), AccessLevel.SUPERVISOR));
        counter++;

        //surrey
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.ADMIN));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(3).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(1).getId(), roleList.get(counter).getId(), permissionList.get(4).getId(), AccessLevel.SUPERVISOR));
        counter++;

        //vancouver
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(1).getId(), AccessLevel.VOLUNTEER));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(2).getId(), AccessLevel.EMPLOYEE));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(3).getId(), AccessLevel.TEAMLEAD));
        counter++;
        rolePermissionList.add(new DbRolePermission(teamList.get(2).getId(), roleList.get(counter).getId(), permissionList.get(4).getId(), AccessLevel.SUPERVISOR));
        counter++;

    }

    private void initUserTeam() {
        //burnaby
        int counter = 0;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(0, userList.get(counter).getId()));
        counter++;

        //surrey
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(1, userList.get(counter).getId()));
        counter++;

        //vancouver
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
        userTeamList.add(new DbUserTeam(2, userList.get(counter).getId()));
        counter++;
    }

    private void initShiftAndUserShift() {

        //burnaby
        int counter = 0;

        //Person 1:
        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 2:
        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));


        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 3:
        shiftList.add(new DbShift(0, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(3, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 4:
        shiftList.add(new DbShift(3, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 18, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 5:
        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 16, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 6:
        shiftList.add(new DbShift(1, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(6, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 7:
        shiftList.add(new DbShift(8, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(5, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;

        //Person 8:
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //surrey
        //Person 9:
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 10:
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 11:
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 17, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 12:
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 16, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 13:
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 14:
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;

        //Person 15:
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;




        //vancouver

        //Person 16:
        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));


        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(9, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 17:
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 16, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 20, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;


        //Person 18:
        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(4, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 19:
        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));


        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 17, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 17, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(2, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;



        //Person 20:
        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,2, 9, 15), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 2, 11, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));


        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,3, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 3, 16, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,4, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 4, 16, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,5, 9, 15), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 5, 11, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));

        shiftList.add(new DbShift(7, TimeUtil.getEpochSecondsFromUserInput(2017, 10,6, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 6, 16, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), shiftList.get(shiftList.size()-1).getId()));
        counter++;
    }

    private void initOneTimeAvailAndUserShift() {
        int counter = 0;

        //Person 1:
        counter++;



        //Person 2:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 12, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 16, 15)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 3:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 15)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 18, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 4:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 14, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 16, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 12, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 10, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 5:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 6:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 12, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 12, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 7:
        counter++;



        //Person 8:
        counter++;




        //surrey

        //Person 9:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 15, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 18, 15)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 10, 00), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 13, 13, 00)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;




        //Person 10:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 12, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 11:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 13, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 10, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 14, 15)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 12:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 15, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 13:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 12, 15)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,10, 13, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 10, 15, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 9, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 11, 11, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 14:
        counter++;



        //Person 15:
         counter++;




        //vancouver

        //Person 16:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,9, 7, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 9, 14, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 17:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 10, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 12, 12, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,13, 14, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 10, 13, 16, 30)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;



        //Person 18:
        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,11, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 11, 9, 12, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));

        oneTimeAvailList.add(new DbOneTimeAvailability(counter, TimeUtil.getEpochSecondsFromUserInput(2017, 10,12, 8, 30), TimeUtil.getEpochSecondsFromUserInput(2017, 12, 10, 12, 45)));
        userShiftList.add(new DbUserShift(userTeamList.get(counter).getId(), oneTimeAvailList.get( oneTimeAvailList.size()-1).getId()));
        counter++;


        //Person 19:
        counter++;


        //Person 20:
        counter++;
    }

    private void initUserQualification() {
        int counter = 0;


        //burnaby
        //person 1
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        counter++;

        //person 2
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(1).getId()));
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        //person 3
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 4
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 5
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 6
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(3).getId()));
        counter++;


        //person 7
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(5).getId()));
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 8
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;



        //surrey
        //person 9
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(0).getId()));
        counter++;

        //person 10
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 11
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 12
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 13
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 14
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 15
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;


        //vancouver
        //person 16
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 17
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 18
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 19
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(2).getId()));
        counter++;

        //person 20
        userQualificationList.add(new DbUserQualification(counter, qualificationList.get(4).getId()));
        counter++;
    }

    private void initShiftQualification() {
        int counter = 0;
        //Information and Lost and found to kiosk  -> qualification
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(0).getId()));
        counter++;

        //Speed Watch/Moving Traffic"
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(1).getId()));
        counter++;

        //Community Presence
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(2).getId()));
        counter++;

        //Safety Screen
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(2).getId()));
        counter++;

        //Theft Prevention
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(2).getId()));
        counter++;

        //Auto Theft Prevention
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(2).getId()));
        counter++;

        //Bike Presence
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(3).getId()));
        counter++;

        //Special Events (peaceful protests, campus ceremonies, etc)
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(4).getId()));
        counter++;

        //Smoking Checks
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), qualificationList.get(5).getId()));
        counter++;

        //Pedestrian Safety
        shiftQualificationList.add(new DbShiftQualification(shiftTypeList.get(counter).getId(), -1));
        counter++;
    }


}
