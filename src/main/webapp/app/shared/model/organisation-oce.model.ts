import { IActionServiceOce } from 'app/shared/model/action-service-oce.model';
import { LegalStatus } from 'app/shared/model/enumerations/legal-status.model';

export interface IOrganisationOce {
  id?: number;
  name?: string;
  description?: any;
  legalId?: string;
  accreditation?: string;
  additionalInformation?: any;
  legalStatus?: LegalStatus;
  uuidOce?: string;
  uuidOrganisation?: string;
  uuidEntity?: string;
  archived?: boolean;
  deleted?: boolean;
  actionServices?: IActionServiceOce[];
}

export class OrganisationOce implements IOrganisationOce {
  constructor(
    public id?: number,
    public name?: string,
    public description?: any,
    public legalId?: string,
    public accreditation?: string,
    public additionalInformation?: any,
    public legalStatus?: LegalStatus,
    public uuidOce?: string,
    public uuidOrganisation?: string,
    public uuidEntity?: string,
    public archived?: boolean,
    public deleted?: boolean,
    public actionServices?: IActionServiceOce[]
  ) {
    this.archived = this.archived || false;
    this.deleted = this.deleted || false;
  }
}
