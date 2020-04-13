import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { NatureService } from 'app/shared/model/enumerations/nature-service.model';

export interface IActionServiceOce {
  id?: number;
  name?: string;
  description?: any;
  nature?: NatureService;
  missionObjective?: any;
  amount?: number;
  beneficiaryNumber?: number;
  volunteerNumber?: number;
  additionalInformation?: any;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  startDate?: Moment;
  endDate?: Moment;
  labelAdresse?: string;
  streetNumberAdresse?: string;
  routeAdresse?: string;
  locality?: string;
  county?: string;
  country?: string;
  postalCode?: string;
  latitude?: string;
  longitude?: string;
  typeServiceId?: number;
  users?: IUser[];
  organisationId?: number;
}

export class ActionServiceOce implements IActionServiceOce {
  constructor(
    public id?: number,
    public name?: string,
    public description?: any,
    public nature?: NatureService,
    public missionObjective?: any,
    public amount?: number,
    public beneficiaryNumber?: number,
    public volunteerNumber?: number,
    public additionalInformation?: any,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public labelAdresse?: string,
    public streetNumberAdresse?: string,
    public routeAdresse?: string,
    public locality?: string,
    public county?: string,
    public country?: string,
    public postalCode?: string,
    public latitude?: string,
    public longitude?: string,
    public typeServiceId?: number,
    public users?: IUser[],
    public organisationId?: number
  ) {}
}
