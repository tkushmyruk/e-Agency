<#import "parts/common.ftlh" as c/>
<#include "parts/security.ftlh"/>
<@c.main>
  <h2>E-Agency</h2>
  <a href="/login">Sign in</a>
  <a href="/catalog?page=1&size=4">Catalog of tours</a>
  <#if user?exists>
    <a href="/user/${user.getUsername()}">My Account</a>
  </#if>
  <#if isAdmin>
    <a href="/user-list?page=1&size=4">User List</a>
  </#if>
</@c.main>