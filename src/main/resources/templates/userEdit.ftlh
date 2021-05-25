<#import "parts/common.ftlh" as c>
<@c.main>
  <div>User Redactor</div>
  <form method="post">
    <input type="text" value="${user.username}" name="username">
    <#list roles as role>
      <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", " ")}>${role}</label>
      </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <button type="submit">save</button>
  </form>
</@c.main>