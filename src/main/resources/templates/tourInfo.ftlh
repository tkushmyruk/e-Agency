<#import "parts/common.ftlh" as c/>
<@c.main>
  <div>
    <p><h3 align="center">${tour.getTourName()}</h3></p>
    <h4>Tour for <#list tour.getTourType() as type>${type.toString()}</#list></h4>
    <#if tour.isHot()>
      <div>This is ~~~HOT~~~ tour</div>
    </#if>
    <div>Only for ${tour.getPrice()} you can buy it</div>
    <div>This tour for ${tour.getCountOfPeople()} people</div>
    <div>You can arrive in ${tour.getCountry()}, ${tour.getLocality()}</div>
    <div>And have a nice time at ${tour.getHotelName()} hotel with
      <#list tour.getHotelStars() as star>${star.toString()} </#list> stars in room
      <#list tour.getRoomType() as room >${room.toString()}</#list> class</div>
    <div>Your trip start at ${tour.getStartDate().toString()} and end ${tour.getEndDate().toString()}</div>
    <div>Your plane will wait you in ${tour.getDepartingFrom()}</div>
    <#if tour.isAllInclusive()>
    <div>This is all inclusive tour</div>
    </#if>
  </div>
  <#if tour.getTourStatus() == 'Registered'>
  <div>
    <form method="post">
      <button type="submit">Buy this tour</button>
    </form>
  </div>
  </#if>
</@c.main>