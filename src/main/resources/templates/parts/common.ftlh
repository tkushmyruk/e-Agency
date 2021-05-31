<#include "security.ftlh"/>
<#import "login.ftlh" as p>
<#macro main>
  <!DOCTYPE html>
  <html>
  <head>
    <title>E-University</title>
    <meta charset="UTF-8"/>
  </head>
  <body>
  <h2>E-Agency</h2>
  <#if !user??>
  <a href="/login">Sign in</a>
    <a href="/registration">Registration</a>
  </#if>
  <a href="/catalog?page=0&amp;size=4">Catalog of tours</a>
  <#if user??>
    <@p.logout/>
    <a href="/user-account">My Account</a>
  </#if>
  <#if isAdmin>
    <a href="/user-list?page=1&amp;size=4;">User List</a>
  </#if>
  <#nested/>
  </body>
  </html>
</#macro>