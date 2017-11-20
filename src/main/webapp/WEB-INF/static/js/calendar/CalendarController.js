$(document).ready(function () {
    var date;
    const now = moment();

    const CalendarController = {
      /**
      * Draw calendar
      */
      setCurrentCalendar: () => {
        $("#month-name").html(date.format('MMMM') + "<br><span style='font-size:18px' id='year-name'>" + date.format('YYYY') + "</span>");

        let startDay = date.startOf('month').day() - 1;
        if (startDay == -1)
          startDay = 6;

        $(".days").append("<tr>");

        for (var i = 0; i < startDay; i++) {
          $(".days").append("<td></td>");
        }

        for (var i = 0; i < date.daysInMonth(); i++) {
          if ((date.format('MMMM YYYY') == now.format('MMMM YYYY')) && ((i + 1) == now.format("D"))) {
            $(".days").append("<td class='calendar-day'><span class='active-calendar-day'>" +  (i + 1) + "</span></td>");
          } else {
            $(".days").append("<td class='calendar-day'>" +  (i + 1) + "</td>");
          }

          if (++startDay == 7) {
             $(".days").append("</tr><tr>");
             startDay = 0;
          }
        }

        while(startDay++ < 7) {
          $(".days").append("<td></td>");
        }
      },

      /**
      * Button initialisation
      */
      initButtons: () => {
        $('#calendar-prev').on("click", function () {
          date = date.subtract(1, "month")
          $(".days").html("");
          CalendarController.setCurrentCalendar();
        });
        $('#calendar-next').on("click", function () {
          date = date.add(1, "month")
          $(".days").html("");
          CalendarController.setCurrentCalendar();
        });
      },

      /**
      * Events handlers initialisation
      */
      initHandlers: () => {
        $('#editCalendarForm #chooseCalendar').on('change', function () {
          $.ajax({
            type: 'GET',
            url: 'calendar/' + $(this).val(),
            data: $(this).val(),
            dataType: 'json',
            success: function (result) {
              $('#editName').val(result.name);
              $('#editDescription').val(result.description);
              $('input').iCheck('uncheck');
              if (result.private) {
                $('#editIsPrivate').iCheck('check');
              }
            },
          });
        });
      },

      /**
      * Initialize function.
      *
      * @return void
      */
      init: () => {
        date = moment();
        CalendarController.setCurrentCalendar();
        CalendarController.initButtons();
        CalendarController.initHandlers();
      }
    };

    CalendarController.init();
});