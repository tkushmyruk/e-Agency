<#import "parts/common.ftlh" as c/>
<@c.main>
  <h2>Your tours</h2>
  <div>
    <br/>
    <#list boughtTours as tour>
      <div id="tours">
        <div>${tour.getTourName()}</div>
        <div><a href="/tour/${tour.getTourName()}">More about it</a> </div>
        <div>
          <form method="post">
            <input type="hidden" name="tourName" value="${tour.getTourName()}"/>
            <button type="submit">Return it back</button>
          </form>
        </div>
      </div>
        <br/>
    </#list>
  </div>
</@c.main>