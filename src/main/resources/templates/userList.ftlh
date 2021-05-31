<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>
<@c.main>
  <div>List of users</div>
  <table>
    <thead>
    <tr>
      <th>Name</th>
      <th>Role</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <#list page.content as user>
      <tr id="userList">
        <td>${user.username}</td>
        <td><#list user.roles as role>${role} <#sep>, </#list></td>
        <td><a href="/user-list/${user.username}">edit</a></td>
        <#if !user.isEnabled()><td>Blocked!</td>
        <td>
          <form method="post" action="/user-active">
            <input type="hidden" name="username" value="${user.username}"/>
            <button type="submit">Active</button>
          </form>
          </td>
        </#if>
        <td>
          <form method="post" action="/user-block">
            <input type="hidden" name="username" value="${user.username}"/>
            <button type="submit">block</button>
          </form>
        </td>
      </tr>
    </#list>
    </tbody>
    <@p.pager url page/>
  </table>
</@c.main>