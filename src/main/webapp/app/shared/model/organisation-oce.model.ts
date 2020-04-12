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
    public actionServices?: IActionServiceOce[]
  ) {}
}
