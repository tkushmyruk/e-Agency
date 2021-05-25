<#import "parts/common.ftlh" as c>
<@c.main>
  <h2>${message!}</h2>
  <form  method="post">
    <div>
      <label>Password :
        <input type="password" name="password" placeholder="password" value="${user.getPassword()}"/>
      </label>
    </div>
      <div>
        <label>Email :
          <input type="email" name="email" placeholder="email" value="${user.getEmail()}"/>
        </label>
      </div>
    <button type="submit">Save Changes</button>
  </form>
</@c.main>