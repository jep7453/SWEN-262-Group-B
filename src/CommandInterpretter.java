import Command.*;
import Library.Library;
import Library.Day;

import java.util.*;


public class CommandInterpretter {

    private Day currentDay;

    public CommandInterpretter() {
        GregorianCalendar startDay = new GregorianCalendar();
        startDay.setTime(new Date());
        this.currentDay = new Day(startDay);
    }

    public Command interpret(Library library, String request) {
        Command command;
        String[] parts = request.split(",");
        GregorianCalendar requestDate = new GregorianCalendar();
        requestDate.setTime(new Date());

        while(parts[parts.length-1].charAt(parts[parts.length-1].length()-1)!=(';')) {
            System.out.println("partial-request;");
            Scanner input = new Scanner(System.in);
            request = request + input.nextLine();
            parts = request.split(",");
        }
        parts[parts.length-1]=parts[parts.length-1].substring(0,parts[parts.length-1].length()-1);
        currentDay.updateDay(parts);
        switch (parts[0]) {
            case "register":
               command = new RegisterVisitor(library,parts[1],parts[2],parts[3],parts[4]);
               return command;
            case "purchase":
                ArrayList<Integer> books = new ArrayList<>();
                int iter = 0;
                for(String part: parts) {
                    if(iter>1) {
                        books.add(Integer.valueOf(part));
                    }
                    iter++;
                }
                command = new PurchaseBook(library,Integer.valueOf(parts[1]),books);
                return command;
            case "search":
                ArrayList<String> search = new ArrayList<>();
                iter = 0;
                String authors = "";
                boolean inAuthors = false;
                for(String part: parts) {
                    if(iter>0) {
                        if(part.charAt(0)==('{')) {
                            authors = part.substring(1);
                            if(part.charAt(part.length()-1)!=('}'))
                                inAuthors = true;
                            else
                                search.add(part);
                        }
                        else if(inAuthors) {
                            if(part.charAt(part.length()-1)!=('}')) {
                                authors=authors + part;
                            }
                            else {
                                authors=authors + part.substring(0,part.length()-2);
                                inAuthors=false;
                                search.add(authors);
                            }
                        }
                        else
                            search.add(part);
                    }
                    iter++;
                }
                command = new BookStoreSearch(library,search);
                return command;
            case "info":
                ArrayList<String> info = new ArrayList<>();
                iter = 0;
                authors = "";
                inAuthors = false;
                for(String part: parts) {
                    if(iter>0) {
                        if(part.charAt(0)==('{')) {
                            authors = part.substring(1);
                            if(part.charAt(part.length()-1)!=('}'))
                                inAuthors = true;
                            else
                                info.add(part);
                        }
                        else if(inAuthors) {
                            if(part.charAt(part.length()-1)!=('}')) {
                                authors=authors + part;
                            }
                            else {
                                authors=authors + part.substring(0,part.length()-2);
                                inAuthors=false;
                                info.add(authors);
                            }

                        }
                        else
                            info.add(part);
                    }
                    iter++;
                }
                command = new LibraryBookSearch(library,info);
                return command;
            case "report":
                if(parts.length == 1) {
                    command = new LibraryReport(library, library.getHistory());//if they want the full history
                }
                else{
                    ArrayList<Day> reportDays = new ArrayList<>();
                    ArrayList<Day> history = library.getHistory();
                    try {
                        for (int i = 0; i <= Integer.valueOf(parts[1]); i++) {
                            reportDays.add(history.get((history.size() - 1) - i));//gets the day objects from the top of the list which is the most recent days
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("The Library has not existed for that many days!!");
                    }
                    command = new LibraryReport(library, reportDays);
                }
                return command;
            case "advance":

                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(new Date());

                String advancement = parts[1] + ",";
                for(int i = 1; i <= Integer.valueOf(parts[1]); i++){//i would be the number of days we have already advanced
                    GregorianCalendar skippedDate = new GregorianCalendar();
                    skippedDate.setTime(new Date());
                    skippedDate.set(Calendar.DAY_OF_MONTH, skippedDate.get(Calendar.DAY_OF_MONTH) + i);//make sure the skipped days have the correct date
                    Day skippedDayObj = new Day(skippedDate);
                    library.addToHistory(skippedDayObj);
                    if(i == Integer.valueOf(parts[1])){//the day we advanced to
                        this.currentDay = skippedDayObj;//if we advanced the correct amount of days already then set the last created date obj to current day
                    }
                    else{
                        library.addToHistory(skippedDayObj);//if we just want to skip the day then add directly to the history
                    }
                }
                if(parts.length > 2){
                    advancement += parts[2];
                }
                command = new AdvanceTime(calendar,advancement,library);
                return command;
            case "datetime":
                command = new CurrentDateTime(library);
                return command;
            default:
                return null;
        }
    }
}
