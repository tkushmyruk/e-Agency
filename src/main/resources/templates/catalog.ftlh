<#import "parts/common.ftlh" as c/>
<#import "parts/pager.ftlh" as p/>
<#include "parts/security.ftlh"/>
<@c.main>
  <h2>Catalog of tours</h2>
  <a href="/tour/add">Add Tour</a>
  <br/>
  <div>
    <h4>Sorting</h4>
    <form method="post" action="/tour-type-sort">
      <div>By Tour Type</div>
      <label> Rest <input type="radio" id="rest" name="tourType" value="REST"/></label>
      <label> Excursion <input type="radio" id="excursion" name="tourType" value="EXCURSION"/></label>
      <label> Shopping <input type="radio" id="shopping" name="tourType" value="SHOPPING"/></label>
      <button type="submit">Sort</button>
    </form>
    <form method="post" action="/tour-hotel-stars-sort">
      <div>By Hotel Stars</div>
      <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
      <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
      <button type="submit">Sort</button>
    </form>
    <form method="post" action="/tour-price-sort">
      <div>By Price</div>
      <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
      <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
      <button type="submit">Sort</button>
    </form>
    <form method="post" action="/tour-people-sort">
      <div>By Count of People</div>
      <label>Ascending<input type="radio" id="Asc" name="direction" value="Asc"/></label>
      <label>Descending<input type="radio" id="Desc" name="direction" value="Desc"/></label>
      <button type="submit">Sort</button>
    </form>
  </div>
  <div>
    <br/>
    <#list catalog as tour>
      <#if tour.getTourStatus() == "Registered">
        <div id="tour-list">
          <div>${tour.getTourName()}</div>
          <div><a href="/tour/${tour.getTourName()}">More about it</a></div>
          <#if isAdmin || isManager>
          <div><a href="/tour-redact/${tour.getTourName()}">Redact</a></div>
          <div>
            <form method="post" action="/delete-tour/${tour.getTourName()}">
              <button type="submit">Cancel</button>
            </form>
          </div>
          </#if>
        </div>
        <br/>
      </#if>
    </#list>
  </div>
</@c.main>