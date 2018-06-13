package nl.overnightprojects.kids_in_space.Objects;

import java.nio.Buffer;

import nl.overnightprojects.kids_in_space.Utils.MeshObject;

public class Moonbase extends MeshObject {
    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;

    private int indicesNumber = 0;
    private int verticesNumber = 0;

    public Moonbase() {
        setVerts();
        setTexCoords();
        setNorms();
        setIndices();
    }

    private void setVerts() {
        double[] OBJECT_VERTS = {
                2.150593f, 2.198399f, 21.548649f, 2.188962f, 2.198399f, 48.664495f, -34.647160f, 4.083346f, 32.123725f, -39.629383f, 4.083346f, 25.061487f, -1.625913f, 63.942249f, 3.011497f, -10.107922f, 62.040292f, 1.921113f, -8.796082f, 62.040292f, 5.903705f, -1.637384f, 62.040292f, 8.317344f, 10.361644f, 58.953018f, 3.126604f, 6.553211f, 62.040292f, 1.921113f, 5.218428f, 62.040292f, 5.903705f, 8.646561f, 58.953018f, 8.502069f, -1.264825f, 59.160629f, 11.912967f, -1.637384f, 62.040292f, 8.317344f, -8.796082f, 62.040292f, 5.903705f, -11.121285f, 58.953018f, 8.502069f, -12.631978f, 58.953018f, -6.201955f, -9.969816f, 62.040292f, -4.976290f, -8.796082f, 62.040292f, -8.110806f, -11.121285f, 58.953018f, -10.449910f, 2.153136f, 22.088868f, -44.185082f, 2.188962f, 2.198399f, -49.314341f, -34.647160f, 4.083346f, -33.403016f, -31.560676f, 21.014080f, -30.316533f, 43.706344f, 21.014080f, -30.316533f, 46.792771f, 4.083346f, -33.402960f, -1.264825f, 59.160629f, 11.912967f, 51.533365f, 4.083346f, 13.816166f, 46.792771f, 4.083346f, 32.123725f, 51.894849f, 4.083346f, -15.436372f, 55.667795f, 4.083346f, -2.150310f, 2.117083f, 2.198399f, -2.099735f, 2.147654f, 2.198399f, -22.171483f, 6.654982f, 62.040292f, -4.340346f, -1.625009f, 64.087249f, -5.457289f, -1.615855f, 65.600937f, -1.615911f, 7.717338f, 62.040292f, -1.552170f, -13.088396f, 58.953018f, 2.239254f, -10.324462f, 62.040292f, 1.263752f, -11.251989f, 62.040292f, -1.552170f, -14.282247f, 58.953018f, -1.561494f, 10.492404f, 58.953018f, -5.340091f, 11.857362f, 58.953018f, -1.561494f, -10.207942f, 62.040292f, -4.340346f, -11.251989f, 62.040292f, -1.552170f, -72.332205f, 4.083346f, -2.150310f, -68.507215f, 4.083346f, -15.436372f, 47.520146f, 23.289217f, -13.888949f, 44.161688f, 38.033815f, -12.701031f, 46.792771f, 38.033815f, -1.968975f, 50.647145f, 23.289217f, -2.047748f, -35.756587f, 25.731337f, 9.535719f, -32.309637f, 38.033815f, 8.569540f, -34.647160f, 38.033815f, -1.969032f, -38.554708f, 31.634646f, -2.047748f, 43.291573f, 23.289217f, 28.622527f, 40.603643f, 38.033815f, 25.934597f, 2.117083f, 42.083991f, 37.953065f, 2.148276f, 24.761767f, 42.604997f, 15.555435f, 57.342362f, -16.628867f, 40.603643f, 38.033815f, -27.213832f, 2.117083f, 42.083991f, -39.028870f, -1.191817f, 57.723962f, -22.605580f, -28.458032f, 38.033815f, -27.213832f, -17.773386f, 57.342362f, -16.628867f, -34.658687f, 23.289217f, -18.529297f, -31.415902f, 38.033815f, -15.148914f, -28.458032f, 38.033815f, -27.213832f, -31.145905f, 23.289217f, -29.901762f, -28.458032f, 38.033815f, 25.934597f, -31.145962f, 23.289217f, 28.622527f, 47.220595f, 23.289217f, 12.239924f, 43.909604f, 38.033815f, 11.029798f, 40.603643f, 38.033815f, 25.934597f, 43.291573f, 23.289217f, 28.622527f, 47.731487f, 21.014080f, 12.426627f, 43.706288f, 21.014080f, 29.037298f, 2.153136f, 22.088868f, 43.322822f, -31.560676f, 21.014080f, 29.037298f, -35.119004f, 21.014080f, -21.767731f, -31.560676f, 21.014080f, -30.316533f, 43.706288f, 21.014080f, 29.037298f, -64.976068f, 21.014080f, 9.684788f, -35.756587f, 25.731337f, 9.535719f, -38.554708f, 31.634646f, -2.047748f, -67.845277f, 26.917389f, -2.059897f, 48.038383f, 21.014080f, -14.072262f, 51.241895f, 21.014080f, -2.059897f, 43.291573f, 23.289217f, -29.901762f, 2.148333f, 24.761767f, -43.495851f, -31.145905f, 23.289217f, -29.901762f, -31.560676f, 21.014080f, 29.037298f, -31.145962f, 23.289217f, 28.622527f, -35.131379f, 23.289217f, 14.179345f, -35.691037f, 21.014080f, 17.382179f, 48.395911f, 21.014080f, 9.684788f, 47.869141f, 23.289217f, 9.535719f, 44.455305f, 38.033815f, 8.569540f, -28.458032f, 38.033815f, 25.934597f, -31.763937f, 38.033815f, 11.029798f, -11.121285f, 58.953018f, 8.502069f, -8.796082f, 62.040292f, 5.903705f, -10.107922f, 62.040292f, 1.921113f, -12.809697f, 58.953018f, 3.126604f, 2.144207f, 2.198399f, 17.072739f, 52.315892f, 4.083346f, 10.794215f, 6.773537f, 62.040292f, 1.263752f, -1.623992f, 64.256209f, 2.135675f, 10.644694f, 58.953018f, 2.239311f, -10.324462f, 62.040292f, 1.263752f, -68.934079f, 4.083346f, 10.794215f, 43.706344f, 21.014080f, -30.316533f, 43.291573f, 23.289217f, -29.901762f, 46.806954f, 23.289217f, -16.589820f, 47.307731f, 21.014080f, -16.812180f, -64.615658f, 21.014080f, -14.072318f, -35.405105f, 25.731337f, -13.889005f, -34.658687f, 23.289217f, -18.529297f, -35.119004f, 21.014080f, -21.767731f, -35.405105f, 25.731337f, -13.889005f, -32.016020f, 38.033815f, -12.701031f, 40.603643f, 38.033815f, -27.213832f, 43.561513f, 38.033815f, -15.148914f, 2.188962f, 2.198399f, -49.314341f, 2.154605f, 2.198399f, -26.749673f, -38.770909f, 4.083346f, -29.712120f, -34.647160f, 4.083346f, -33.403016f, -1.637384f, 62.040292f, -10.651984f, -8.796082f, 62.040292f, -8.110806f, -9.969816f, 62.040292f, -4.976290f, -1.627100f, 63.741983f, -6.333507f, 8.646561f, 58.953018f, -10.449910f, 5.218428f, 62.040292f, -8.110806f, 6.412675f, 62.040292f, -4.976290f, 10.181044f, 58.953018f, -6.201955f, 46.792771f, 4.083346f, -33.402960f, 51.034284f, 4.083346f, -18.466799f, -12.938479f, 58.953018f, -5.340091f, -10.207942f, 62.040292f, -4.340346f, -35.691037f, 21.014080f, 17.382179f, -35.131379f, 23.289217f, 14.179345f, -39.629383f, 4.083346f, 25.061487f, -35.691037f, 21.014080f, 17.382179f, -64.976068f, 21.014080f, 9.684788f, -68.934079f, 4.083346f, 10.794215f, 55.667795f, 4.083346f, -2.150310f, 52.315892f, 4.083346f, 10.794215f, 51.034284f, 4.083346f, -18.466799f, 51.894849f, 4.083346f, -15.436372f, -72.332205f, 4.083346f, -2.150310f, -67.845277f, 26.917389f, -2.059897f, -64.615658f, 21.014080f, -14.072318f, -68.507215f, 4.083346f, -15.436372f, -68.507215f, 4.083346f, -15.436372f, -64.615658f, 21.014080f, -14.072318f, -35.119004f, 21.014080f, -21.767731f, -38.770909f, 4.083346f, -29.712120f, 46.792771f, 4.083346f, -33.402960f, 51.533365f, 4.083346f, 13.816166f, -34.647160f, 4.083346f, 32.123725f, -39.629383f, 4.083346f, 25.061487f, -68.934079f, 4.083346f, 10.794215f, -64.976068f, 21.014080f, 9.684788f, 46.792771f, 4.083346f, 32.123725f, 2.188962f, 2.198399f, 48.664495f, -38.770909f, 4.083346f, -29.712120f, -34.647160f, 4.083346f, -33.403016f, -34.647160f, 4.083346f, 32.123725f, 46.792771f, 4.083346f, 32.123725f, -20.595523f, 57.342362f, 6.174210f, -21.061378f, 57.342362f, 4.684255f, 46.792771f, 38.033815f, -1.968975f, 20.921237f, 57.342362f, -1.697961f, 18.894680f, 57.342362f, 4.684255f, 44.455305f, 38.033815f, 8.569540f, 43.561513f, 38.033815f, -15.148914f, 18.119838f, 57.342362f, -9.493112f, 18.640167f, 57.342362f, -8.045369f, 44.161688f, 38.033815f, -12.701031f, -23.056799f, 57.342362f, -1.697961f, -20.810707f, 57.342362f, -8.045369f, -20.298403f, 57.342362f, -9.493112f, 40.603643f, 38.033815f, -27.213832f, 15.555435f, 57.342362f, -16.628867f, 18.421593f, 57.342362f, 6.174210f, 43.909604f, 38.033815f, 11.029798f, -17.773386f, 57.342362f, 15.200619f, -1.264825f, 59.160629f, -13.998010f, -1.191817f, 57.723962f, -22.605580f, -17.773386f, 57.342362f, -16.628867f, -11.121285f, 58.953018f, -10.449910f, 40.603643f, 38.033815f, 25.934597f, 15.555435f, 57.342362f, 15.200619f, -1.191817f, 57.723962f, 20.952430f, 2.117083f, 42.083991f, 37.953065f, -17.773386f, 57.342362f, -16.628867f, -1.191817f, 57.723962f, 20.952430f, -17.773386f, 57.342362f, 15.200619f, 5.218428f, 62.040292f, -8.110806f, 8.646561f, 58.953018f, -10.449910f, -1.264825f, 59.160629f, -13.998010f, -1.637384f, 62.040292f, -10.651984f, -11.121285f, 58.953018f, -10.449910f, -8.796082f, 62.040292f, -8.110806f, -20.595523f, 57.342362f, 6.174210f, -12.809697f, 58.953018f, 3.126604f, -13.088396f, 58.953018f, 2.239254f, -21.061378f, 57.342362f, 4.684255f, -23.056799f, 57.342362f, -1.697961f, -14.282247f, 58.953018f, -1.561494f, -12.938479f, 58.953018f, -5.340091f, -20.810707f, 57.342362f, -8.045369f, -12.631978f, 58.953018f, -6.201955f, -20.298403f, 57.342362f, -9.493112f, -17.773386f, 57.342362f, 15.200619f, -11.121285f, 58.953018f, 8.502069f,
        };
        mVertBuff = fillBuffer(OBJECT_VERTS);
        verticesNumber = OBJECT_VERTS.length / 3;
    }

    private void setTexCoords() {
        double[] OBJECT_TEX_COORDS = {
                0.318146f, 0.315089f, 0.439974f, 0.363630f, 0.304357f, 0.512398f, 0.264347f, 0.523851f, 0.113471f, 0.735995f, 0.097686f, 0.696634f, 0.117670f, 0.697022f, 0.138544f, 0.728587f, 0.132791f, 0.796768f, 0.120629f, 0.778661f, 0.136968f, 0.766019f, 0.154963f, 0.780131f, 0.397343f, 0.789653f, 0.378946f, 0.781436f, 0.378365f, 0.742498f, 0.397343f, 0.735827f, 0.381968f, 0.712887f, 0.400941f, 0.707784f, 0.403504f, 0.724770f, 0.385267f, 0.735827f, 0.537389f, 0.153777f, 0.439974f, 0.158554f, 0.477461f, 0.000000f, 0.558036f, 0.009352f, 0.558036f, 0.379146f, 0.477461f, 0.400123f, 0.156788f, 0.726055f, 0.365658f, 0.061881f, 0.439974f, 0.117753f, 0.234902f, 0.007562f, 0.300845f, 0.013155f, 0.211898f, 0.272755f, 0.121816f, 0.236537f, 0.092191f, 0.788706f, 0.074753f, 0.748852f, 0.091604f, 0.742456f, 0.106380f, 0.789687f, 0.380972f, 0.669846f, 0.400167f, 0.675950f, 0.398141f, 0.689228f, 0.378365f, 0.687827f, 0.094328f, 0.810318f, 0.113453f, 0.811278f, 0.068970f, 0.705686f, 0.080258f, 0.696296f, 0.087695f, 0.633423f, 0.034403f, 0.591013f, 0.842512f, 0.101151f, 0.914926f, 0.107306f, 0.911914f, 0.162915f, 0.838933f, 0.162520f, 0.899750f, 0.628221f, 0.839799f, 0.625729f, 0.845246f, 0.574942f, 0.881053f, 0.575456f, 0.568010f, 0.755629f, 0.500122f, 0.733665f, 0.531072f, 0.576363f, 0.615538f, 0.590556f, 0.743868f, 0.238838f, 0.639035f, 0.358060f, 0.635316f, 0.148975f, 0.734609f, 0.147465f, 0.639035f, 0.018753f, 0.743868f, 0.075090f, 0.908736f, 0.484357f, 0.838662f, 0.505168f, 0.832635f, 0.441297f, 0.901478f, 0.423415f, 0.558739f, 0.407932f, 0.631189f, 0.404540f, 0.842855f, 0.236169f, 0.915215f, 0.229933f, 0.918999f, 0.306777f, 0.847352f, 0.320616f, 0.831689f, 0.237131f, 0.836296f, 0.322752f, 0.628572f, 0.592746f, 0.642369f, 0.404017f, 0.919554f, 0.467400f, 0.912100f, 0.420656f, 0.578486f, 0.759018f, 0.742891f, 0.767143f, 0.879817f, 0.772782f, 0.862837f, 0.834260f, 0.725562f, 0.829445f, 0.831338f, 0.100202f, 0.827671f, 0.162459f, 0.568864f, 0.376328f, 0.550480f, 0.153135f, 0.568864f, 0.010608f, 0.910075f, 0.720077f, 0.899481f, 0.718651f, 0.908649f, 0.649842f, 0.919464f, 0.665480f, 0.830929f, 0.222998f, 0.842112f, 0.222230f, 0.914590f, 0.217248f, 0.830822f, 0.709413f, 0.838527f, 0.637585f, 0.385267f, 0.640218f, 0.403504f, 0.654070f, 0.400640f, 0.672850f, 0.381580f, 0.665649f, 0.298037f, 0.307076f, 0.353391f, 0.052659f, 0.117932f, 0.780748f, 0.109332f, 0.737218f, 0.129131f, 0.799515f, 0.094388f, 0.696570f, 0.151481f, 0.640218f, 0.836296f, 0.016011f, 0.847352f, 0.018162f, 0.843328f, 0.087154f, 0.832174f, 0.086001f, 0.742972f, 0.889656f, 0.879879f, 0.893581f, 0.884295f, 0.916728f, 0.882977f, 0.932574f, 0.899785f, 0.509693f, 0.839885f, 0.518127f, 0.918999f, 0.032106f, 0.915613f, 0.094622f, 0.000000f, 0.187561f, 0.101270f, 0.228276f, 0.019816f, 0.421261f, 0.010109f, 0.394646f, 0.051965f, 0.757501f, 0.053706f, 0.718384f, 0.066396f, 0.707827f, 0.070909f, 0.750311f, 0.068464f, 0.809019f, 0.073004f, 0.787381f, 0.088955f, 0.788483f, 0.089965f, 0.810098f, 0.145726f, 0.000000f, 0.219860f, 0.006287f, 0.381299f, 0.708233f, 0.400421f, 0.704338f, 0.882947f, 0.730684f, 0.884295f, 0.748055f, 0.378365f, 0.808473f, 0.288124f, 0.810016f, 0.297577f, 0.655308f, 0.378365f, 0.640218f, 0.743868f, 0.162007f, 0.747705f, 0.228718f, 0.749172f, 0.077426f, 0.748187f, 0.093135f, 0.547144f, 0.855659f, 0.439974f, 0.848672f, 0.465759f, 0.784822f, 0.547144f, 0.784239f, 0.550057f, 0.790094f, 0.627927f, 0.784239f, 0.638393f, 0.918142f, 0.547144f, 0.918142f, 0.754027f, 0.000000f, 0.748600f, 0.244292f, 0.988914f, 0.730684f, 1.000000f, 0.700123f, 0.532544f, 0.916060f, 0.452482f, 0.904178f, 0.656439f, 0.784239f, 0.725562f, 0.609043f, 1.000000f, 0.422935f, 0.991150f, 0.400123f, 0.725562f, 0.400123f, 0.754027f, 0.338643f, 0.732013f, 0.610973f, 0.733077f, 0.603930f, 0.169291f, 0.991991f, 0.126050f, 0.856733f, 0.152389f, 0.837028f, 0.214172f, 0.964420f, 0.104687f, 0.996173f, 0.086614f, 0.854823f, 0.093939f, 0.855178f, 0.116686f, 0.995396f, 0.737639f, 0.573763f, 0.732966f, 0.539458f, 0.731900f, 0.531634f, 0.045548f, 1.000000f, 0.050515f, 0.853075f, 0.158538f, 0.832428f, 0.224649f, 0.957983f, 0.725562f, 0.653639f, 0.038527f, 0.765550f, 0.000000f, 0.779586f, 0.004620f, 0.688988f, 0.041243f, 0.711696f, 0.288124f, 0.918988f, 0.195789f, 0.804558f, 0.198804f, 0.713192f, 0.288124f, 0.709622f, 0.726648f, 0.493069f, 0.439974f, 0.521210f, 0.447851f, 0.431026f, 0.427031f, 0.722070f, 0.409084f, 0.737340f, 0.403504f, 0.683258f, 0.422376f, 0.684620f, 0.409084f, 0.640218f, 0.427031f, 0.653215f, 0.104810f, 0.640336f, 0.100883f, 0.682690f, 0.096450f, 0.682670f, 0.097368f, 0.640314f, 0.065491f, 0.640218f, 0.077458f, 0.682586f, 0.062063f, 0.694961f, 0.039614f, 0.660951f, 0.058551f, 0.697784f, 0.033711f, 0.665680f, 0.149894f, 0.640472f, 0.127743f, 0.682809f,
        };
        mTexCoordBuff = fillBuffer(OBJECT_TEX_COORDS);
    }

    private void setNorms() {
        double[] OBJECT_NORMS = {
                -0.000763f, -0.999817f, 0.018036f, -0.027375f, -0.544023f, 0.838618f, -0.602588f, -0.526170f, 0.599963f, -0.534562f, -0.632862f, 0.560076f, 0.003876f, 0.973144f, 0.230110f, -0.471633f, 0.850917f, 0.231269f, -0.373394f, 0.850978f, 0.369274f, -0.007080f, 0.877041f, 0.480331f, 0.388409f, 0.912839f, 0.125858f, 0.412397f, 0.885983f, 0.211982f, 0.322367f, 0.878536f, 0.352428f, 0.241768f, 0.946104f, 0.215430f, 0.003449f, 0.931181f, 0.364452f, -0.007080f, 0.877041f, 0.480331f, -0.373394f, 0.850978f, 0.369274f, -0.263344f, 0.933378f, 0.243782f, -0.451582f, 0.877102f, -0.163335f, -0.466353f, 0.846187f, -0.257759f, -0.370006f, 0.840388f, -0.395978f, -0.272469f, 0.926450f, -0.259590f, -0.032929f, 0.246895f, -0.968474f, -0.026887f, -0.548387f, -0.835780f, -0.560778f, -0.550584f, -0.618336f, -0.642659f, 0.317606f, -0.697195f, 0.705405f, 0.256844f, -0.660604f, 0.637715f, -0.500809f, -0.585223f, 0.003449f, 0.931181f, 0.364452f, 0.772759f, -0.605731f, 0.189398f, 0.647603f, -0.498642f, 0.576128f, 0.770196f, -0.603076f, -0.207526f, 0.839595f, -0.543077f, -0.009766f, 0.005249f, -0.999969f, -0.000122f, 0.003357f, -0.999817f, -0.017731f, 0.439985f, 0.865658f, -0.238746f, 0.004608f, 0.964019f, -0.265694f, 0.005066f, 0.999878f, -0.013764f, 0.490158f, 0.871548f, -0.011597f, -0.458357f, 0.876644f, 0.146245f, -0.501846f, 0.833033f, 0.232704f, -0.561632f, 0.827265f, -0.012879f, -0.416059f, 0.909268f, -0.007416f, 0.387432f, 0.910825f, -0.142338f, 0.353740f, 0.935301f, -0.006348f, -0.496841f, 0.828150f, -0.259407f, -0.561632f, 0.827265f, -0.012879f, -0.826411f, -0.562944f, -0.009217f, -0.646840f, -0.501755f, -0.574267f, 0.940306f, 0.236427f, -0.244697f, 0.806970f, 0.549730f, -0.215827f, 0.843349f, 0.537248f, -0.009980f, 0.968993f, 0.246773f, -0.010956f, -0.614887f, 0.659230f, 0.432783f, -0.873257f, 0.421216f, 0.244911f, -0.892331f, 0.451186f, -0.010315f, -0.650258f, 0.759667f, -0.006012f, 0.717277f, 0.260170f, 0.646352f, 0.650655f, 0.515549f, 0.557512f, -0.018708f, 0.491317f, 0.870754f, -0.035218f, 0.252754f, 0.966857f, 0.311472f, 0.887875f, -0.338572f, 0.643147f, 0.513108f, -0.568377f, -0.018128f, 0.484878f, -0.874386f, -0.034669f, 0.858882f, -0.510941f, -0.683035f, 0.422101f, -0.596057f, -0.442701f, 0.815821f, -0.372051f, -0.645192f, 0.625080f, -0.439283f, -0.879299f, 0.404981f, -0.250587f, -0.683035f, 0.422101f, -0.596057f, -0.696951f, 0.308847f, -0.647145f, -0.691488f, 0.425367f, 0.583819f, -0.710379f, 0.310617f, 0.631519f, 0.947691f, 0.227485f, 0.223792f, 0.812220f, 0.549425f, 0.195990f, 0.650655f, 0.515549f, 0.557512f, 0.717277f, 0.260170f, 0.646352f, 0.945463f, 0.227912f, 0.232612f, 0.715903f, 0.259621f, 0.648091f, -0.033662f, 0.253059f, 0.966857f, -0.672994f, 0.317331f, 0.668081f, -0.627796f, 0.521500f, -0.577807f, -0.642659f, 0.317606f, -0.697195f, 0.715903f, 0.259621f, 0.648091f, -0.555406f, 0.544572f, 0.628407f, -0.614887f, 0.659230f, 0.432783f, -0.650258f, 0.759667f, -0.006012f, -0.659108f, 0.752007f, -0.002289f, 0.937895f, 0.236457f, -0.253761f, 0.968902f, 0.247108f, -0.011078f, 0.707541f, 0.257210f, -0.658162f, -0.034577f, 0.246773f, -0.968444f, -0.696951f, 0.308847f, -0.647145f, -0.672994f, 0.317331f, 0.668081f, -0.710379f, 0.310617f, 0.631519f, -0.654134f, 0.626270f, 0.424085f, -0.652242f, 0.521073f, 0.550462f, 0.943602f, 0.235908f, 0.232154f, 0.945738f, 0.235969f, 0.223334f, 0.812464f, 0.548997f, 0.196112f, -0.691488f, 0.425367f, 0.583819f, -0.887173f, 0.401196f, 0.227912f, -0.263344f, 0.933378f, 0.243782f, -0.373394f, 0.850978f, 0.369274f, -0.471633f, 0.850917f, 0.231269f, -0.455855f, 0.878079f, 0.145421f, 0.003479f, -0.999847f, 0.016907f, 0.774438f, -0.603412f, 0.190069f, 0.443678f, 0.870235f, 0.214026f, 0.004639f, 0.970641f, 0.240455f, 0.390362f, 0.911893f, 0.126499f, -0.501846f, 0.833033f, 0.232704f, -0.655568f, -0.501968f, 0.564074f, 0.705405f, 0.256844f, -0.660604f, 0.707541f, 0.257210f, -0.658162f, 0.942167f, 0.228370f, -0.245155f, 0.939665f, 0.228858f, -0.254219f, -0.547716f, 0.548906f, -0.631397f, -0.609546f, 0.657552f, -0.442763f, -0.645192f, 0.625080f, -0.439283f, -0.627796f, 0.521500f, -0.577807f, -0.609546f, 0.657552f, -0.442763f, -0.865291f, 0.424726f, -0.266152f, 0.643147f, 0.513108f, -0.568377f, 0.806452f, 0.550584f, -0.215583f, -0.026887f, -0.548387f, -0.835780f, -0.001007f, -0.999817f, -0.018830f, -0.513138f, -0.602588f, -0.611164f, -0.560778f, -0.550584f, -0.618336f, -0.008179f, 0.861843f, -0.507065f, -0.370006f, 0.840388f, -0.395978f, -0.466353f, 0.846187f, -0.257759f, 0.003906f, 0.967040f, -0.254494f, 0.249123f, 0.940977f, -0.229072f, 0.321543f, 0.868465f, -0.377239f, 0.408551f, 0.881558f, -0.236427f, 0.385327f, 0.911832f, -0.141575f, 0.637715f, -0.500809f, -0.585223f, 0.768700f, -0.605213f, -0.206854f, -0.454268f, 0.875546f, -0.164312f, -0.496841f, 0.828150f, -0.259407f, -0.652242f, 0.521073f, 0.550462f, -0.654134f, 0.626270f, 0.424085f, -0.534562f, -0.632862f, 0.560076f, -0.652242f, 0.521073f, 0.550462f, -0.555406f, 0.544572f, 0.628407f, -0.655568f, -0.501968f, 0.564074f, 0.839595f, -0.543077f, -0.009766f, 0.774438f, -0.603412f, 0.190069f, 0.768700f, -0.605213f, -0.206854f, 0.770196f, -0.603076f, -0.207526f, -0.826411f, -0.562944f, -0.009217f, -0.659108f, 0.752007f, -0.002289f, -0.547716f, 0.548906f, -0.631397f, -0.646840f, -0.501755f, -0.574267f, -0.646840f, -0.501755f, -0.574267f, -0.547716f, 0.548906f, -0.631397f, -0.627796f, 0.521500f, -0.577807f, -0.513138f, -0.602588f, -0.611164f, 0.637715f, -0.500809f, -0.585223f, 0.772759f, -0.605731f, 0.189398f, -0.602588f, -0.526170f, 0.599963f, -0.534562f, -0.632862f, 0.560076f, -0.655568f, -0.501968f, 0.564074f, -0.555406f, 0.544572f, 0.628407f, 0.647603f, -0.498642f, 0.576128f, -0.027375f, -0.544023f, 0.838618f, -0.513138f, -0.602588f, -0.611164f, -0.560778f, -0.550584f, -0.618336f, -0.602588f, -0.526170f, 0.599963f, 0.647603f, -0.498642f, 0.576128f, -0.544450f, 0.826136f, 0.145024f, -0.551866f, 0.820856f, 0.146977f, 0.843349f, 0.537248f, -0.009980f, 0.432417f, 0.901608f, -0.006592f, 0.389569f, 0.914853f, 0.105899f, 0.812464f, 0.548997f, 0.196112f, 0.806452f, 0.550584f, -0.215583f, 0.381207f, 0.917142f, -0.116062f, 0.385815f, 0.915067f, -0.117405f, 0.806970f, 0.549730f, -0.215827f, -0.616230f, 0.787500f, -0.008423f, -0.545030f, 0.822535f, -0.162358f, -0.537187f, 0.828089f, -0.160100f, 0.643147f, 0.513108f, -0.568377f, 0.311472f, 0.887875f, -0.338572f, 0.385174f, 0.916868f, 0.104740f, 0.812220f, 0.549425f, 0.195990f, -0.449416f, 0.816187f, 0.363079f, 0.003815f, 0.925138f, -0.379589f, -0.034669f, 0.858882f, -0.510941f, -0.442701f, 0.815821f, -0.372051f, -0.272469f, 0.926450f, -0.259590f, 0.650655f, 0.515549f, 0.557512f, 0.314249f, 0.889676f, 0.331187f, -0.034120f, 0.864681f, 0.501144f, -0.018708f, 0.491317f, 0.870754f, -0.442701f, 0.815821f, -0.372051f, -0.034120f, 0.864681f, 0.501144f, -0.449416f, 0.816187f, 0.363079f, 0.321543f, 0.868465f, -0.377239f, 0.249123f, 0.940977f, -0.229072f, 0.003815f, 0.925138f, -0.379589f, -0.008179f, 0.861843f, -0.507065f, -0.272469f, 0.926450f, -0.259590f, -0.370006f, 0.840388f, -0.395978f, -0.544450f, 0.826136f, 0.145024f, -0.455855f, 0.878079f, 0.145421f, -0.458357f, 0.876644f, 0.146245f, -0.551866f, 0.820856f, 0.146977f, -0.616230f, 0.787500f, -0.008423f, -0.416059f, 0.909268f, -0.007416f, -0.454268f, 0.875546f, -0.164312f, -0.545030f, 0.822535f, -0.162358f, -0.451582f, 0.877102f, -0.163335f, -0.537187f, 0.828089f, -0.160100f, -0.449416f, 0.816187f, 0.363079f, -0.263344f, 0.933378f, 0.243782f,
        };
        mNormBuff = fillBuffer(OBJECT_NORMS);
    }

    private void setIndices() {
        short[] OBJECT_INDICES = {
                0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7, 8, 9, 10, 8, 10, 11, 12, 13, 14, 12, 14, 15, 16, 17, 18, 16, 18, 19, 20, 21, 22, 20, 22, 23, 24, 25, 21, 24, 21, 20, 11, 10, 7, 11, 7, 26, 9, 4, 7, 9, 7, 10, 1, 0, 27, 1, 27, 28, 29, 30, 31, 29, 31, 32, 33, 34, 35, 33, 35, 36, 37, 38, 39, 37, 39, 40, 41, 33, 36, 41, 36, 42, 34, 43, 44, 34, 44, 35, 32, 31, 45, 32, 45, 46, 47, 48, 49, 47, 49, 50, 51, 52, 53, 51, 53, 54, 55, 56, 57, 55, 57, 58, 59, 60, 61, 59, 61, 62, 62, 61, 63, 62, 63, 64, 65, 66, 67, 65, 67, 68, 58, 57, 69, 58, 69, 70, 71, 72, 73, 71, 73, 74, 75, 71, 74, 75, 74, 76, 77, 58, 70, 77, 70, 78, 79, 65, 68, 79, 68, 80, 81, 55, 58, 81, 58, 77, 82, 83, 84, 82, 84, 85, 86, 47, 50, 86, 50, 87, 60, 88, 89, 60, 89, 61, 61, 89, 90, 61, 90, 63, 91, 92, 93, 91, 93, 94, 95, 96, 71, 95, 71, 75, 96, 97, 72, 96, 72, 71, 92, 98, 99, 92, 99, 93, 100, 101, 102, 100, 102, 103, 0, 104, 105, 0, 105, 27, 106, 107, 4, 106, 4, 9, 108, 106, 9, 108, 9, 8, 107, 109, 5, 107, 5, 4, 104, 0, 3, 104, 3, 110, 111, 112, 113, 111, 113, 114, 115, 116, 117, 115, 117, 118, 119, 120, 66, 119, 66, 65, 112, 121, 122, 112, 122, 113, 123, 124, 125, 123, 125, 126, 127, 128, 129, 127, 129, 130, 131, 132, 133, 131, 133, 134, 132, 127, 130, 132, 130, 133, 135, 136, 124, 135, 124, 123, 137, 138, 17, 137, 17, 16, 40, 39, 138, 40, 138, 137, 54, 53, 120, 54, 120, 119, 85, 84, 116, 85, 116, 115, 114, 113, 47, 114, 47, 86, 113, 122, 48, 113, 48, 47, 124, 32, 46, 124, 46, 125, 130, 129, 43, 130, 43, 34, 134, 133, 33, 134, 33, 41, 133, 130, 34, 133, 34, 33, 136, 29, 32, 136, 32, 124, 31, 104, 110, 31, 110, 45, 35, 44, 109, 35, 109, 107, 42, 36, 106, 42, 106, 108, 36, 35, 107, 36, 107, 106, 30, 105, 104, 30, 104, 31, 50, 49, 97, 50, 97, 96, 87, 50, 96, 87, 96, 95, 139, 140, 83, 139, 83, 82, 93, 99, 52, 93, 52, 51, 103, 102, 38, 103, 38, 37, 141, 142, 143, 141, 143, 144, 145, 87, 95, 145, 95, 146, 147, 114, 86, 147, 86, 148, 149, 150, 151, 149, 151, 152, 153, 154, 155, 153, 155, 156, 157, 111, 114, 157, 114, 147, 146, 95, 75, 146, 75, 158, 159, 91, 94, 159, 94, 160, 148, 86, 87, 148, 87, 145, 161, 162, 150, 161, 150, 149, 163, 81, 77, 163, 77, 164, 165, 79, 80, 165, 80, 166, 164, 77, 78, 164, 78, 167, 158, 75, 76, 158, 76, 168, 88, 24, 20, 88, 20, 89, 89, 20, 23, 89, 23, 90, 99, 169, 170, 99, 170, 52, 171, 172, 173, 171, 173, 174, 175, 176, 177, 175, 177, 178, 53, 179, 180, 53, 180, 120, 120, 180, 181, 120, 181, 66, 182, 183, 176, 182, 176, 175, 174, 173, 184, 174, 184, 185, 98, 186, 169, 98, 169, 99, 187, 188, 189, 187, 189, 190, 131, 183, 188, 131, 188, 187, 178, 177, 172, 178, 172, 171, 52, 170, 179, 52, 179, 53, 191, 192, 193, 191, 193, 194, 66, 181, 195, 66, 195, 67, 57, 196, 197, 57, 197, 69, 185, 184, 192, 185, 192, 191, 198, 199, 200, 198, 200, 201, 201, 200, 202, 201, 202, 203, 204, 205, 206, 204, 206, 207, 172, 42, 108, 172, 108, 173, 176, 134, 41, 176, 41, 177, 208, 209, 210, 208, 210, 211, 211, 210, 212, 211, 212, 213, 183, 131, 134, 183, 134, 176, 173, 108, 8, 173, 8, 184, 214, 215, 205, 214, 205, 204, 177, 41, 42, 177, 42, 172, 207, 206, 209, 207, 209, 208, 192, 11, 26, 192, 26, 193, 213, 212, 190, 213, 190, 189, 193, 26, 215, 193, 215, 214, 184, 8, 11, 184, 11, 192,
        };
        mIndBuff = fillBuffer(OBJECT_INDICES);
        indicesNumber = OBJECT_INDICES.length;
    }

    public int getNumObjectIndex() {
        return indicesNumber;
    }

    @Override
    public int getNumObjectVertex() {
        return verticesNumber;
    }

    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType) {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;
        }
        return result;
    }
}
