<#import "parts/common.ftlh" as c/>
<@c.main>
<h2>Add Tour</h2>
  <div>
    <form method="post">
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Tour Name: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="text" name="tourName"
                 class="form-control ${(tourNameError??)?string('is-invalid', '')}" placeholder="tour name"/>
          <#if tourNameError??>
            <div class="invalid-feedback">
              ${tourNameError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Count of People: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="text" name="countOfPeople"
                 class="form-control ${(countOfPeopleError??)?string('is-invalid', '')}" placeholder="how many people?"/>
          <#if countOfPeopleError??>
            <div class="invalid-feedback">
              ${countOfPeopleError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Price: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="number" step="0.01" name="price"
                 class="form-control ${(priceError??)?string('is-invalid', '')}" placeholder="price"/>
          <#if priceError??>
            <div class="invalid-feedback">
              ${priceError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Start Date: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="date" name="startDate"
                 class="form-control ${(startDateError??)?string('is-invalid', '')}" placeholder="start date"/>
          <#if startDateError??>
            <div class="invalid-feedback">
              ${startDateError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">End Date: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="date" name="endDate"
                 class="form-control ${(endDateError??)?string('is-invalid', '')}" placeholder="end date"/>
          <#if endDateError??>
            <div class="invalid-feedback">
              ${endDateError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Departing from: </label>
        <div class="col-sm-4 mt-0.5">
          <input type="text" name="departingFrom"
                 class="form-control ${(departingFromError??)?string('is-invalid', '')}" placeholder="departing from"/>
          <#if  departingFromError??>
            <div class="invalid-feedback">
              ${departingFromError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Country: </label>
        <div class="col-sm-4">
          <input type="text" name="country"
                 class="form-control ${(countryError??)?string('is-invalid', '')}" placeholder="country"/>
          <#if countryError??>
            <div class="invalid-feedback">
              ${countryError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Locality: </label>
        <div class="col-sm-4">
          <input type="text" name="locality"
                 class="form-control ${(localityError??)?string('is-invalid', '')}" placeholder="locality"/>
          <#if localityError??>
            <div class="invalid-feedback">
              ${localityError}
            </div>
          </#if>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label-2">Hotel Name: </label>
        <div class="col-sm-4">
          <input type="text" name="hotelName"
                 class="form-control ${(hotelNameError??)?string('is-invalid', '')}" placeholder="hotel name"/>
          <#if hotelNameError??>
            <div class="invalid-feedback">
              ${hotelNameError}
            </div>
          </#if>
        </div>
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
    <button class="btn btn-primary">Save</button>
    </form>
  </div>
</@c.main>