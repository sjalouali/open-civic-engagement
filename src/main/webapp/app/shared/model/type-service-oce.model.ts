export interface ITypeServiceOce {
  id?: number;
  nameShort?: string;
  nameLong?: string;
  description?: any;
  typeServiceColor?: string;
}

export class TypeServiceOce implements ITypeServiceOce {
  constructor(
    public id?: number,
    public nameShort?: string,
    public nameLong?: string,
    public description?: any,
    public typeServiceColor?: string
  ) {}
}
