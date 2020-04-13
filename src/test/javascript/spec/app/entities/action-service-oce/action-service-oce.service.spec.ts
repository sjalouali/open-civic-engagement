import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ActionServiceOceService } from 'app/entities/action-service-oce/action-service-oce.service';
import { IActionServiceOce, ActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { NatureService } from 'app/shared/model/enumerations/nature-service.model';

describe('Service Tests', () => {
  describe('ActionServiceOce Service', () => {
    let injector: TestBed;
    let service: ActionServiceOceService;
    let httpMock: HttpTestingController;
    let elemDefault: IActionServiceOce;
    let expectedResult: IActionServiceOce | IActionServiceOce[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ActionServiceOceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ActionServiceOce(
        0,
        'AAAAAAA',
        'AAAAAAA',
        NatureService.DEMANDE,
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ActionServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate
          },
          returnedFromService
        );

        service.create(new ActionServiceOce()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ActionServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            description: 'BBBBBB',
            nature: 'BBBBBB',
            missionObjective: 'BBBBBB',
            amount: 1,
            beneficiaryNumber: 1,
            volunteerNumber: 1,
            additionalInformation: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            labelAdresse: 'BBBBBB',
            streetNumberAdresse: 'BBBBBB',
            routeAdresse: 'BBBBBB',
            locality: 'BBBBBB',
            county: 'BBBBBB',
            country: 'BBBBBB',
            postalCode: 'BBBBBB',
            latitude: 'BBBBBB',
            longitude: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ActionServiceOce', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            description: 'BBBBBB',
            nature: 'BBBBBB',
            missionObjective: 'BBBBBB',
            amount: 1,
            beneficiaryNumber: 1,
            volunteerNumber: 1,
            additionalInformation: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            email: 'BBBBBB',
            phoneNumber: 'BBBBBB',
            startDate: currentDate.format(DATE_TIME_FORMAT),
            endDate: currentDate.format(DATE_TIME_FORMAT),
            labelAdresse: 'BBBBBB',
            streetNumberAdresse: 'BBBBBB',
            routeAdresse: 'BBBBBB',
            locality: 'BBBBBB',
            county: 'BBBBBB',
            country: 'BBBBBB',
            postalCode: 'BBBBBB',
            latitude: 'BBBBBB',
            longitude: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            startDate: currentDate,
            endDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ActionServiceOce', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
