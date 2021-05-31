<#import "parts/common.ftlh" as c/>
<@c.main>
  <h2>Add Tour</h2>
  <div>
    <form method="post">
      <div>
        <label>Tour Name : <input type="text" name="tourName" placeholder="Tour Name" value="${tour.getTourName()}"/></label>
      </div>
      <div>
        <label>Count of People : <input type="number" name="countOfPeople" placeholder="Count of People" value="${tour.getCountOfPeople()}"/></label>
      </div>
      <div>
        <label>Price : <input type="number" step="0.01" name="price" placeholder="Price" value="${tour.getPrice()}"/></label>
      </div>
      <div>
        <label>Start Date : <input type="date" name="startDate" placeholder="Start Date" value="${tour.getStartDate()}"/></label>
      </div>
      <div>
        <label>End Date : <input type="date" name="endDate" placeholder="End Date" value="${tour.getEndDate()}"/></label>
      </div>
      <div>
        <label>Departing From : <input type="text" name="departingFrom" placeholder="Departing From" value="${tour.getDepartingFrom()}"/></label>
      </div>
      <div>
        <label>Country : <input type="text" name="country" placeholder="Country" value="${tour.getCountry()}"/></label>
      </div>
      <div>
        <label>Locality : <input type="text" name="locality" placeholder="Locality" value="${tour.getLocality()}"/></label>
      </div>
      <div>
        <label>Hotel Name : <input type="text" name="hotelName" placeholder="Hotel Name" value="${tour.getHotelName()}"/></label>
      </div>
      <div>
        <label>Tour Status : <input type="text" name="tourStatus" placeholder="Tour Status" value="${tour.getTourStatus()}"/></label>
      </div>
      <div>
        <p><b>Tour Type</b></p>
        <div><#list tour.getTourType() as type>
          <input type="radio" id="rest" name="tourType" value="REST"
                 <#if type == "REST">checked</#if>/>
          <label for="rest">Rest</label>
        </div>
        <div>
          <input type="radio" id="excursion" name="tourType" value="EXCURSION"
                <#if type == "EXCURSION">checked</#if>/>
          <label for="excursion">Excursion</label>
        </div>
        <div>
          <input type="radio" id="shopping" name="tourType" value="SHOPPING"
                 <#if type == "SHOPPING">checked</#if> />
          <label for="shopping">Shopping</label>

        </div>
        </#list>
      </div>
      <div>
        <p><b>Room Type</b></p>
        <#list tour.getRoomType() as type>
        <div>
          <input type="radio" id="standard" name="roomType" value="STANDARD"
                 <#if type == "STANDARD">checked</#if> >
          <label for="standard">Standard</label>
        </div>
        <div>
          <input type="radio" id="luxe" name="roomType" value="LUXE"
                 <#if type == "LUXE">checked</#if>>
          <label for="luxe">Luxe</label>
        </div>
        <div>
          <input type="radio" id="president" name="roomType" value="PRESIDENT"
                 <#if type == "PRESIDENT">checked</#if>>
          <label for="president">President</label>
        </div>
        </#list>
      </div>
      <div>
        <p><b>Hotel Stars</b></p>
        <#list tour.getHotelStars() as stars>
        <div>
          <input type="radio" id="one" name="hotelStars" value="ONE"
                 <#if stars == "ONE">checked</#if>>
          <label for="one">One</label>
        </div>
        <div>
          <input type="radio" id="two" name="hotelStars" value="TWO"
                 <#if stars == "TWO">checked</#if>>
          <label for="two">Two</label>
        </div>
        <div>
          <input type="radio" id="three" name="hotelStars" value="THREE"
                 <#if stars == "THREE">checked</#if>>
          <label for="three">Three</label>
        </div>
        <div>
          <input type="radio" id="four" name="hotelStars" value="FOUR"
                 <#if stars == "FOUR">checked</#if>>
          <label for="four">Four</label>
        </div>
        <div>
          <input type="radio" id="five" name="hotelStars" value="FIVE"
                 <#if stars == "FIVE">checked</#if>>
          <label for="five">Five</label>
        </div>
        </#list>
      </div>
      <div>
        <div>
          <input type="checkbox" id="allInclusive" name="isAllInclusive" value="allInclusive"
            <#if tour.isAllInclusive()> checked</#if>>
          <label for="allInclusive">is it all inclusive?</label>
        </div>
      </div>
      <div>
        <div>
          <input type="checkbox" id="hot" name="isHot" value="hot"
            <#if tour.isHot()></#if>checked>
          <label for="hot">is it Hot?</label>
        </div>
      </div>
      <input type="submit" value="save"/>
    </form>
  </div>
</@c.main>