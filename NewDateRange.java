import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class NewDateRange
{
    ArrayList<String> signUpDate = new ArrayList<String>();
    ArrayList<String> currDate = new ArrayList<String>();
    ArrayList<LocalDate> startDateList = new ArrayList<LocalDate>();
    ArrayList<LocalDate> endDateList = new ArrayList<LocalDate>();

    public void input()
    {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int n = sc.nextInt();
        for(int i=0;i<n;i++)
        {
            String[] inputStrings = sc.next().split(" ");
            signUpDate.add(inputStrings[0]);
            currDate.add(inputStrings[1]);
        }
    }
    public void puttingRangesIntoDateLists()
    {
        for(int i=0;i<signUpDate.size();i++)
        {
            String tempSignUpDate = signUpDate.get(i);
            String tempCurrentDate = currDate.get(i);
            calculatingDates(tempSignUpDate,tempCurrentDate);
        }
    }
    public void calculatingDates(String tempSignUpDate, String tempCurrentDate)
    {
        String[] str_su = tempSignUpDate.split("-");
        int su_dd = Integer.parseInt(str_su[0]);
        int su_mm = Integer.parseInt(str_su[1]);
        int su_yy = Integer.parseInt(str_su[2]);

        String[] str_cur = tempCurrentDate.split("-");
        int cur_dd = Integer.parseInt(str_cur[0]);
        int cur_mm = Integer.parseInt(str_cur[1]);
        int cur_yy = Integer.parseInt(str_cur[2]);

        LocalDate startingDate = null;
        LocalDate endingDate = null;
        if(cur_yy-su_yy>1)
        {
            startingDate = LocalDate.of(cur_yy,su_mm,su_dd).minusDays(30);
            endingDate = LocalDate.of(cur_yy,su_mm,su_dd).plusDays(30);
        }
        else if(cur_yy - su_yy == 1)
        {
            startingDate = LocalDate.of(cur_yy,su_mm,su_dd).minusDays(30);
            endingDate = LocalDate.of(cur_yy,su_mm,su_dd).plusDays(30);
        }
        else
        {
            startingDate = LocalDate.of(cur_yy,cur_mm,cur_dd);
            endingDate = startingDate;

        }

        LocalDate currentDate = LocalDate.of(cur_yy,cur_mm,cur_dd);

        if(!((startingDate.compareTo(currentDate)<0 && currentDate.compareTo(endingDate)<0)))
        {
            if(currentDate.compareTo(startingDate)>0)
            {
                startDateList.add(startingDate);
                endDateList.add(endingDate);
            }
            else
            {
                startDateList.add(currentDate);
                endDateList.add(currentDate);
            }
        }
        else if(endingDate.compareTo(currentDate)>0)
        {
            endingDate = currentDate;
            startDateList.add(startingDate);
            endDateList.add(endingDate);
        }
    }

    public void printOutputInDesiredFormat()
    {
        String formattedStartDate;
        String formattedEndDate;
        for(int i=0;i<startDateList.size();i++)
        {
            LocalDate startDate = startDateList.get(i);
            LocalDate endDate = endDateList.get(i);
            if(startDate.compareTo(endDate) == 0)
            {
                System.out.println("No Range");
            }
            else
            {
                formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                System.out.println(formattedStartDate+"\t"+formattedEndDate);
            }

        }
    }

    public static void main(String args[])
    {
        NewDateRange ndr = new NewDateRange();
        ndr.input();
        ndr.puttingRangesIntoDateLists();
        ndr.printOutputInDesiredFormat();
    }
}
