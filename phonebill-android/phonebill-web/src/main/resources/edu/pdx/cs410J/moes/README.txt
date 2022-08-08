This is a README file!
Nathan Moes - PhoneBill-web
Command line input is [options] <customer> <callerNumber> <calleeNumber> <begin date> <begin time> <end date> <end time>
-README option open readme file from this projects resources
-print will show the information related to a created phonecall class object
Phone call aruments from the command line are as to specification from appcalsses document
that being in order - "Customer name" "caller number" "callee number" "call start date" "call start time" "call end date
" "call end time"
Callee name is left as None, as the project specification says nothing as to how to obtain it from command line
The input from command line will filter the phone number to ensure its correct as well as the dates/times
The name input is not checked other than ensuring the input is using characters and spaces
First a phone bill object for the created phone call object to be added to
-textFile is used to specify when there is a file to read from or write to the data for a phone bill
The data written is a representation of the PhoneBill data, such as the Customer name, and the needed values for each
call
If there is no file that matches the name after the textFile then one will be created
If there is a matching file the system will attempt to read in all the data and create a phone bill object from it
Else when a new file is created the new data will be written to if from the new PhoneBill and call
If the name of the customer in the file does not match the name provided then there will be an error printed to the screen
If the -pretty is present on the command line it will then allow the user to print to a file specifed just after
Such as -pretty text.txt which will pretty print to the text file. If the file name is instead just a "-" then it will
print to standard out. The dates and times are now changed to be using the java.util.date class. Thus some modification
has also been made to the command line inputs to include the time with AM/PM at the end.

Now with phonebill web we dont have the file aruments but instead use a server!
With the host and port being passed in at each invokation from the main to connect.
The same features as before being that we will be able to add in calls and view them, but now it will also be
avalible from http requests. Such as get and post requests. This will be done mostly by adding calls in the main function
to the client, but data can be easily requested from the http with valid parameters.
