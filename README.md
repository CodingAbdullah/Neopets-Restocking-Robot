A cheeky robot designed using Selenium (I know Selenium is used for automating tests, not ROBOTS!). Nevertheless, it is a fun exercise to have a go at. 

You can have this robot running in the background while you work on something else so you aren't wasting your entire day looking for those rare items you covet. 

For security reasons, you need to create .env file in the root folder of this project. Store your neopets and gmail account creds here as specified by the dotenv
keys (in the login and JavaMailUtil classes).

Now, this "robot" won't ACTUALLY buy things for you, it will send a real-time email to your account suggesting the rare items (if there are any, perhaps unbuyables) 
along with their prices available in the shop.

It uses the simple SMTP architecture for emails and uses the gmail host. You will have to authorize less secure apps on your gmail account for emails to reach
there. 

TNT (The Neopets team) has build in security features over the years, as developers (like me) can penetrate their systems using scripts and robots. They've set
up a captcha boxes to prevent robot auto-buying (i.e. click on the neopet in the image box). So in the spirit of learning, don't mind that.

Other security techniques include restock bans. So this just means you cannot buy anything from any of the shops for a random alotted time (minutes, hours and 
even in rare cases, days). This doesn't mean your banned from the game, it's a way for TNT to control master restockers or that odd robot ahem, :) from constantly 
snooping for items.

Constantly refreshing pages will lead to a restock ban. To avoid this, the robot is set to refresh the page every 30 or so, seconds with a constant loop having it run 24 hours!  

This may not always be foulproof though. It is possible that all of a sudden, the items in the shop disappear (and then you know you've been banned).

Feel free to tweak and play around with it. New features will be added from time to time. As of right now, the robot works on the food shop, battle and potion
shops.
