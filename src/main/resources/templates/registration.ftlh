<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as p>
<@c.main>
  <div class="mb-1">Add new user</div>
  <@p.login "/registration" true/>
  <a href="/login">Back to Login page</a>
</@c.main>