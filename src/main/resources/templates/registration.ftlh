<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as p>
<@c.main>
  <div>Add new user</div>
  <@p.login "/registration" true/>
</@c.main>