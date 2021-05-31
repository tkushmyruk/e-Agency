<#import "parts/common.ftlh" as c/>
<@c.main>
<h2>Add Tour</h2>
  <div>
    <form method="post">
    <div>
      <label>Tour Name : <input type="text" name="tourName" placeholder="Tour Name"/></label>
    </div>
    <div>
      <label>Count of People : <input type="number" name="countOfPeople" placeholder="Count of People"/></label>
    </div>
    <div>
      <label>Price : <input type="number" step="0.01" name="price" placeholder="Price"/></label>
    </div>
    <div>
      <label>Start Date : <input type="date" name="startDate" placeholder="Start Date"/></label>
    </div>
    <div>
      <label>End Date : <input type="date" name="endDate" placeholder="End Date"/></label>
    </div>
    <div>
      <label>Departing From : <input type="text" name="departingFrom" placeholder="Departing From"/></label>
    </div>
    <div>
      <label>Country : <input type="text" name="country" placeholder="Country"/></label>
    </div>
    <div>
      <label>Locality : <input type="text" name="locality" placeholder="Locality"/></label>
    </div>
    <div>
      <label>Hotel Name : <input type="text" name="hotelName" placeholder="Hotel Name"/></label>
    </div>
    <div>
      <p><b>Tour Type</b></p>
      <div>
        <input type="radio" id="rest" name="tourType" value="REST"
               checked>
        <label for="rest">Rest</label>
      </div>
      <div>
        <input type="radio" id="excursion" name="tourType" value="EXCURSION">
        <label for="excursion">Excursion</label>
      </div>
      <div>
        <input type="radio" id="shopping" name="tourType" value="SHOPPING">
        <label for="shopping">Shopping</label>
      </div>
    </div>
    <div>
      <p><b>Room Type</b></p>
      <div>
        <input type="radio" id="standard" name="roomType" value="STANDARD"
               checked>
        <label for="standard">Standard</label>
      </div>
      <div>
        <input type="radio" id="luxe" name="roomType" value="LUXE">
        <label for="luxe">Luxe</label>
      </div>
      <div>
        <input type="radio" id="president" name="roomType" value="PRESIDENT">
        <label for="president">President</label>
      </div>
    </div>
    <div>
      <p><b>Hotel Stars</b></p>
      <div>
        <input type="radio" id="one" name="hotelStars" value="ONE"
               checked>
        <label for="one">One</label>
      </div>
      <div>
        <input type="radio" id="two" name="hotelStars" value="TWO">
        <label for="two">Two</label>
      </div>
      <div>
        <input type="radio" id="three" name="hotelStars" value="THREE">
        <label for="three">Three</label>
      </div>
      <div>
        <input type="radio" id="four" name="hotelStars" value="FOUR">
        <label for="four">Four</label>
      </div>
      <div>
        <input type="radio" id="five" name="hotelStars" value="FIVE">
        <label for="five">Five</label>
      </div>
    </div>
    <div>
      <div>
        <input type="checkbox" id="allInclusive" name="isAllInclusive" value="allInclusive"
               checked>
        <label for="allInclusive">is it all inclusive?</label>
      </div>
    </div>
    <div>
      <div>
        <input type="checkbox" id="hot" name="isHot" value="hot"
               checked>
        <label for="hot">is it Hot?</label>
      </div>
    </div>
    <input type="submit" value="save"/>
    </form>
  </div>
</@c.main>